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
import io.ktor.client.statement.*
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

    override suspend fun getUser(token: String): Response<Any> {
        return try {
            val responseValidToken = this.validateToken(token)
            if (responseValidToken.code / 100 != 2){
                throw Exception(responseValidToken.error)
            }
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/user"){
                    headers["Authorization"] = token
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

    override suspend fun validateToken(token: String): Response<Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.post("$host/auth/validate"){
                    headers["Authorization"] = token
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

    override suspend fun deleteUser(token: String): Response<Any> {
        return try {
            val responseValidToken = this.validateToken(token)
            if (responseValidToken.code / 100 != 2){
                throw Exception(responseValidToken.error)
            }
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.delete("$host/user"){
                    headers["Authorization"] = token
                }
                if (response.status.value != 204){
                    throw Exception("User was not deleted.")
                }
                Response(successfulOperation = true, code = 204, data = Any())
            }
            Response(successfulOperation = result.successfulOperation, code = result.code, data = null)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun updateUserDetails(token: String, userDetailsId: Int): Response<Any> {
        return try {
            val responseValidToken = this.validateToken(token)
            if (responseValidToken.code / 100 != 2){
                throw Exception(responseValidToken.error)
            }
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.patch("$host/user/details/$userDetailsId"){
                    headers["Authorization"] = token
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
}