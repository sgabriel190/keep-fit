package com.example.user_service.persistence.entities

data class UserEntity(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val idUserDetails: Int,
    val idDietPlan: Int
)
