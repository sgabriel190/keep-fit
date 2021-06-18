package com.example.user_service.business.interfaces

import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.business_models.ForgotPasswordResponse
import com.example.user_service.presentation.http.Response

interface UserServiceInterface {
    fun deleteUser(token: String): Response<Any>
    fun forgotPassword(data: ForgotPasswordRequest, token: String): Response<ForgotPasswordResponse>
    fun getUser(token: String): Response<UserModel>
    fun updateCalories(calories: Int, token: String): Response<UserModel>
    fun updatePlanId(idUserDetails: Int, token: String): Response<UserModel>
}