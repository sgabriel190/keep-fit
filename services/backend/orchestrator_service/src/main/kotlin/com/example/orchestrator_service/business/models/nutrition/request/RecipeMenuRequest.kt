package com.example.orchestrator_service.business.models.plan.request

data class RecipeMenuRequest(
    val recipes: List<Int>,
    val meal: String
)
