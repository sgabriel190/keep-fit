package com.example.user_service.persistence.models

data class UserModel(
    val id: Int,
    val username: String,
    val email: String,
    val idUserDetails: Int? = null,
    val targetCalories: Int
)