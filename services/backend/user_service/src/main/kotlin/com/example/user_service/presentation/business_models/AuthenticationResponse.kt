package com.example.user_service.presentation.business_models

data class AuthenticationResponse(
    val id: Int? = null,
    val username: String? = null,
    val token: String? = null
)
