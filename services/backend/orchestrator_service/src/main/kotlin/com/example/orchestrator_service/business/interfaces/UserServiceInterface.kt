package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.Response

interface UserServiceInterface {
    suspend fun authenticateUser(data: LoginRequest): Response<Any>
    suspend fun registerUser(data: RegisterRequest): Response<Any>
    suspend fun getUser(token: String): Response<Any>
    suspend fun validateToken(token: String): Response<Any>
    suspend fun deleteUser(token: String): Response<Any>
    suspend fun updateUserDetails(token: String, userDetailsId: Int): Response<Any>
}