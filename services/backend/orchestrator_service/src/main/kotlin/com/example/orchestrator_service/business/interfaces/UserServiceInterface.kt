package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.user.request.LoginRequest
import com.example.orchestrator_service.business.models.user.request.RegisterRequest
import com.example.orchestrator_service.business.models.user.response.AuthenticationResponse
import com.example.orchestrator_service.business.models.user.response.RegisterResponse
import com.example.orchestrator_service.business.models.user.response.UserModel
import com.example.orchestrator_service.business.models.user.response.UserProfileResponse
import com.example.orchestrator_service.presentation.http.Response

interface UserServiceInterface {
    suspend fun authenticateUser(data: LoginRequest): Response<AuthenticationResponse>
    suspend fun registerUser(data: RegisterRequest): Response<RegisterResponse>
    suspend fun getUser(token: String): Response<UserModel>
    suspend fun getUserProfile(token: String): Response<UserProfileResponse>
    suspend fun validateToken(token: String): Response<Any>
    suspend fun deleteUser(token: String): Response<out Any>
    suspend fun updateUserDetails(token: String, userDetailsId: Int): Response<UserModel>
}