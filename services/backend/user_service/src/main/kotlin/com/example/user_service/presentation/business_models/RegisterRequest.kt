package com.example.user_service.presentation.business_models

import com.example.user_service.persistence.models.UserModel

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val targetCalories: Int
)

fun RegisterRequest.toUserModel() = UserModel(
    username = username,
    password = password,
    email = email,
    targetCalories = targetCalories
)
