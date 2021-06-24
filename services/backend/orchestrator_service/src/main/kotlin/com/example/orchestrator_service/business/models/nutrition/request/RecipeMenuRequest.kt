package com.example.orchestrator_service.business.models.nutrition.request

data class RecipeMenuRequest(
    val recipes: List<Int>,
    val meal: String
)
