package com.example.orchestrator_service.business.models.user

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val targetCalories: Int
)
