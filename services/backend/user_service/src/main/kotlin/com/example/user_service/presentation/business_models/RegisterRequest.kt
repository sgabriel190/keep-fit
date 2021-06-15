package com.example.user_service.presentation.business_models

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)
