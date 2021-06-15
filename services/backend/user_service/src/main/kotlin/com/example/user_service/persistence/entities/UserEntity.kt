package com.example.user_service.persistence.entities

import com.example.user_service.persistence.models.UserModel

data class UserEntity(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val idUserDetails: Int,
    val idDietPlan: Int
)

fun UserEntity.toUserModel() = UserModel(
    username = this.username,
    password = this.password,
    email = this.email,
    idUserDetails = this.idUserDetails,
    idDietPlan = this.idDietPlan
)