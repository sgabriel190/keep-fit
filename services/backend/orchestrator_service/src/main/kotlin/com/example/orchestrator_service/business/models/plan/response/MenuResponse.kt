package com.example.orchestrator_service.business.models.plan.response

data class MenuResponse(
    val meals: List<MealResponse>,
    val day: String
)
