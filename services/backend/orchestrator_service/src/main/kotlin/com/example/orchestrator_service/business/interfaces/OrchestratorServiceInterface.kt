package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.Response

interface OrchestratorServiceInterface {
    suspend fun authenticateUser(data: LoginRequest): Response<Any>
    suspend fun registerUser(data: RegisterRequest): Response<Any>
    suspend fun getImage(imagePath: String): Response<Any>
    suspend fun getRecipes(params: Map<String, Any>): Response<Any>
    suspend fun getGenders(): Response<Any>
    suspend fun getDietTypes(): Response<Any>
    suspend fun getActivityTypes(): Response<Any>
    suspend fun getActivityType(id: Int): Response<Any>
    suspend fun getUserDetails(id: Int): Response<Any>
}