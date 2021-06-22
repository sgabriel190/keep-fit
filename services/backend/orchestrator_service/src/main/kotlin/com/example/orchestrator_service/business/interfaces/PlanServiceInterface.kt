package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.plan.request.CreateUserPlanRequest
import com.example.orchestrator_service.business.models.plan.response.PlanModelResponse
import com.example.orchestrator_service.presentation.http.Response

interface PlanServiceInterface {
    suspend fun getUserPlan(token: String): Response<PlanModelResponse>
    suspend fun createUserPlan(data: CreateUserPlanRequest, token: String): Response<PlanModelResponse>
    suspend fun deleteUserPlan(token: String): Response<out Any>
}