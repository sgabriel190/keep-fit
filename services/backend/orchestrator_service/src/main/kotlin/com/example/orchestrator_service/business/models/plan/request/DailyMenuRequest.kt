package com.example.orchestrator_service.business.models.plan.request

data class DailyMenuRequest(
    val day: Int,
    val recipes: List<RecipeRequest>
)
