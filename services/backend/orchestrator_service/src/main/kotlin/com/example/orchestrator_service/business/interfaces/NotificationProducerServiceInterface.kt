package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.notification.EmailRequest
import com.example.orchestrator_service.presentation.http.Response

interface NotificationProducerServiceInterface {
    suspend fun sendEmail(data: EmailRequest): Response<Any>
}