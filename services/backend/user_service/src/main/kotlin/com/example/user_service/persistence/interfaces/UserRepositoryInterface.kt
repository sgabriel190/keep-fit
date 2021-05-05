package com.example.user_service.persistence.interfaces

import com.example.user_service.persistence.entities.UserEntity

interface UserRepositoryInterface {
    fun getByUsername(username: String): UserEntity?
    fun getByEmail(email: String): UserEntity?
}