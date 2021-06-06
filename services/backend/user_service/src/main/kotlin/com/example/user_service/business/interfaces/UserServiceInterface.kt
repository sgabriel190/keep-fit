package com.example.user_service.business.interfaces

import com.example.user_service.persistence.models.UserModel

interface UserServiceInterface {
    fun login(username: String, password: String): Boolean
    fun register(data: UserModel): Boolean
    fun deleteUser(id: Int): Boolean
    fun forgotPassword(id: Int): String
}