package com.example.user_service.persistence.data_values.requests

data class ForgotPasswordRequest(
    val username: String,
    val email: String
)
