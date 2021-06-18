package com.example.orchestrator_service.business.models.plan

data class DailyMenuRequest(
    val day: Int,
    val recipes: List<RecipeRequest>
)
