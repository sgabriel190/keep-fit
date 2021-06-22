package com.example.orchestrator_service.business.models.user.response

data class UserModel(
    val id: Int,
    val username: String,
    val email: String,
    val idUserDetails: Int? = null
)
