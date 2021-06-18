package com.example.orchestrator_service.business.models.notification

data class EmailRequest(
    val message: String,
    val to: String,
    val subject: String
)
