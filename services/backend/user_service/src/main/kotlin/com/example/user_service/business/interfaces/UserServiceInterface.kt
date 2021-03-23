package com.example.user_service.business.interfaces

interface UserServiceInterface {
    fun login(id: Int): Boolean
    fun register(name: String): Boolean
    fun deleteUser(id: Int): Boolean
    fun forgotPassword(id: Int): String
}