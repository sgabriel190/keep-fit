package com.example.user_service.persistence.models

data class UserModel(
    val username: String,
    val password: String,
    val email: String,
    val idUserDetails: Int = -1,
    val idDietPlan: Int = -1
)