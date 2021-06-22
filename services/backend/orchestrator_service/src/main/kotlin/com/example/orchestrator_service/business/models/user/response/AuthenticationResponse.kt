package com.example.orchestrator_service.business.models.user.response

data class AuthenticationResponse(
    val id: Int,
    val username: String,
    val token: String
)
