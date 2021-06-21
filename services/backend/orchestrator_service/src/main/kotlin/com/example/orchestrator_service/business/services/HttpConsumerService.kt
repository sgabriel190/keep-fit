package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import org.springframework.stereotype.Service

@Service
class HttpConsumerService: HttpConsumerServiceInterface() {
    override suspend fun <T> executeRequest(block: suspend () -> T): T {
        return block()
    }
}