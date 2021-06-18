package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.consumers.UserConsumerService
import com.example.orchestrator_service.business.interfaces.OrchestratorServiceInterface
import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrchestratorService: OrchestratorServiceInterface {
    @Autowired
    lateinit var userConsumerService: UserConsumerService

    override suspend fun authenticateUser(data: LoginRequest): Response<Any> {
        return try {
            userConsumerService.post("auth/login", body = data)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }
}