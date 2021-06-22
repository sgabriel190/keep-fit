package com.example.orchestrator_service.business.models.plan.request

data class CreateUserPlanRequest(
    val recipeAmount: Int,
    val planDays: Int,
    val description: String
)