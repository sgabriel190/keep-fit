package com.example.user_service.business.interfaces

import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.AuthenticationResponse
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.business_models.RegisterResponse
import com.example.user_service.presentation.http.Response

interface AuthServiceInterface {
    fun login(username: String, password: String): Response<AuthenticationResponse>
    fun register(data: RegisterRequest): Response<RegisterResponse>
}