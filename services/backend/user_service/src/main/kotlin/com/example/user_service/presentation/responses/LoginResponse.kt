package com.example.user_service.presentation.responses

data class LoginResponse(
    val idUser: Int,
    val user: String,
    val jwtToken: String
)
