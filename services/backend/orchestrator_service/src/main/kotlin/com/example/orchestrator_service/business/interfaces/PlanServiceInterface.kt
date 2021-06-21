package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.plan.UserPlanRequest
import com.example.orchestrator_service.presentation.http.Response

interface PlanServiceInterface {
    suspend fun getUserPlan(idUser: Int, token: String): Response<out Any>
    suspend fun getMeals(): Response<out Any>
    suspend fun getMealRecipes(): Response<out Any>
    suspend fun getPlans(): Response<out Any>
    suspend fun getMenus(): Response<out Any>
    suspend fun createPlan(idUser: Int, data: UserPlanRequest): Response<out Any>
}