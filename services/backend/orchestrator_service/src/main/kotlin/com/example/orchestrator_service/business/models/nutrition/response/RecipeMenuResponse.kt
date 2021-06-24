package com.example.orchestrator_service.business.models.nutrition.response

data class RecipeMenuResponse(
    val recipes: List<RecipeLiteResponse>,
    val meal: String
)
