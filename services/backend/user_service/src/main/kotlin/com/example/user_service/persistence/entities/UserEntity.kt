package com.example.user_service.persistence.entities

import com.example.user_service.persistence.models.UserModel

class UserEntity(
    var id: Int,
    var userName: String,
    var passWord: String,
    var email: String,
    var idUserDetails: Int? = null,
    var targetCalories: Int
){
}


fun UserEntity.toUserModel() = UserModel(
    username = this.userName,
    password = this.passWord,
    email = this.email,
    idUserDetails = this.idUserDetails,
    targetCalories = this.targetCalories
)