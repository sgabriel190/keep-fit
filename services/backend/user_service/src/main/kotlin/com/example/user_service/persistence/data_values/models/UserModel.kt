package com.example.user_service.persistence.data_values.models

data class UserModel(
    val username: String,
    val password: String,
    val email: String,
    val idUserDetails: Int,
    val idDietPlan: Int
)