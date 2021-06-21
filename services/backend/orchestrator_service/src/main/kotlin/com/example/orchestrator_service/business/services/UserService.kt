package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NotificationProducerServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.notification.EmailRequest
import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.request.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService: UserServiceInterface {
    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    @Autowired
    lateinit var notificationProducerService: NotificationProducerServiceInterface

    private val host = Host("http://localhost:2022/api/users")

    override suspend fun authenticateUser(data: LoginRequest): Response<Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.post("$host/auth/login"){
                    this.setBodyJson(data)
                }
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun registerUser(data: RegisterRequest): Response<Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.put("$host/auth/register"){
                    this.setBodyJson(data)
                }
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            val tmp = notificationProducerService.sendEmail(EmailRequest("Welcome", data.email, "Register"))
            if (tmp.code / 100 != 2){
                throw Exception(tmp.message + " " + tmp.error)
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }
}