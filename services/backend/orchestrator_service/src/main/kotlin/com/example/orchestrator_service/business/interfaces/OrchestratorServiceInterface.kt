package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.Response

interface OrchestratorServiceInterface {
    suspend fun authenticateUser(data: LoginRequest): Response<Any>
    suspend fun registerUser(data: RegisterRequest): Response<Any>
    suspend fun getImage(imagePath: String): Response<Any>
}