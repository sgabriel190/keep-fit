package com.example.orchestrator_service.business.models.user.request

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)
