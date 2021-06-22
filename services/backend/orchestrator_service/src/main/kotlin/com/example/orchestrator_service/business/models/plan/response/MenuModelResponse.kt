package com.example.orchestrator_service.business.models.plan.response

data class MenuModelResponse(
    val meals: List<MealModelResponse>,
    val day: String
)
