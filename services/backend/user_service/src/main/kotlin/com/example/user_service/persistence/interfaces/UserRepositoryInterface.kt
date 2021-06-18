package com.example.user_service.persistence.interfaces

import com.example.user_service.persistence.entities.UserEntity
import com.example.user_service.persistence.models.UserModel

interface UserRepositoryInterface: RepositoryInterface<UserEntity, UserModel> {
    fun getByUsername(username: String): UserEntity?
    fun getByEmail(email: String): UserEntity?
    fun updateCalories(calories: Int, id: Int)
    fun updatePlanId(idUserDetails: Int, id: Int)
}