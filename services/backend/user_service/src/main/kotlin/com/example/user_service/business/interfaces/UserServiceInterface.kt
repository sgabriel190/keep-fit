package com.example.user_service.business.interfaces

import com.example.user_service.persistence.data_values.models.UserModel

interface UserServiceInterface {
    fun login(id: Int): Boolean
    fun register(name: String): Boolean
    fun deleteUser(id: Int): Boolean
    fun forgotPassword(id: Int): String
    fun test(id: Int): UserModel
}