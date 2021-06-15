package com.example.user_service.business.interfaces

import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.http.Response

interface AuthenticationServiceInterface {
    fun signIn(username: String?, password: String?): Response<UserModel>
}