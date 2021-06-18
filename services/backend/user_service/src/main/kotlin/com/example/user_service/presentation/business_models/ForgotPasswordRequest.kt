package com.example.user_service.presentation.business_models

data class ForgotPasswordRequest(
    val username: String,
    val email: String
)
