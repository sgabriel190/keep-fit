package com.example.user_service.presentation.requests

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)
