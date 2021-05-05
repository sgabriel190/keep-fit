package com.example.user_service.presentation.requests

data class ForgotPasswordRequest(
    val username: String,
    val email: String
)
