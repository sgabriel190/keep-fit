package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NotificationProducerServiceInterface
import com.example.orchestrator_service.business.models.notification.EmailRequest
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.request.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationProducerService: NotificationProducerServiceInterface {
    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    private val host = Host(
        host = "http://${System.getenv("HOST_NOTIFICATION") ?: "localhost"}",
        port = "5000",
        path = "api/notification"
    )

    override suspend fun sendEmail(data: EmailRequest): Response<out Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.post("$host/send/mail"){
                    this.setBodyJson(data)
                }
                response
            }
            if (result.code / 100 != 2){
                throw Exception(result.message + " " + result.error)
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }
}