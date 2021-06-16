package com.example.user_service.business.interfaces

import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.business_models.ForgotPasswordResponse
import com.example.user_service.presentation.http.Response

interface UserServiceInterface {
    fun deleteUser(id: Int, token: String): Response<Any>
    fun forgotPassword(data: ForgotPasswordRequest, token: String): Response<ForgotPasswordResponse>
    fun getUser(id: Int, token: String): Response<UserModel>
    fun updateCalories(calories: Int, id: Int, token: String): Response<UserModel>
}