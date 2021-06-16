package com.example.user_service.presentation.business_models

data class ForgotPasswordResponse(
    val id: Int,
    val username: String,
    val email: String
)
