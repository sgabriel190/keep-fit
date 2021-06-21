package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.Response

interface UserServiceInterface {
    suspend fun authenticateUser(data: LoginRequest): Response<out Any>
    suspend fun registerUser(data: RegisterRequest): Response<out Any>
    suspend fun getUser(token: String): Response<out Any>
    suspend fun validateToken(token: String): Response<out Any>
    suspend fun deleteUser(token: String): Response<out Any>
    suspend fun updateUserDetails(token: String, userDetailsId: Int): Response<out Any>
}