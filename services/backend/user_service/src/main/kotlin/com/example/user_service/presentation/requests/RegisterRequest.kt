package com.example.user_service.persistence.data_values.requests

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)
