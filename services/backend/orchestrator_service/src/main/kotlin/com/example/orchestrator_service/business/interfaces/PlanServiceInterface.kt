package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.presentation.http.Response

interface PlanServiceInterface {
    suspend fun getUserPlan(idUser: Int, token: String): Response<out Any>
}