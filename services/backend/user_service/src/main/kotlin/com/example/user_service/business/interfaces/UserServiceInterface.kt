package com.example.user_service.business.interfaces

import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.http.Response

interface UserServiceInterface {
    fun deleteUser(id: Int): Response<UserModel>
    fun forgotPassword(data: ForgotPasswordRequest): Response<UserModel>
}