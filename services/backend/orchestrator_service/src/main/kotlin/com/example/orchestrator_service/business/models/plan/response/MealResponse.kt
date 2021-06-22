package com.example.orchestrator_service.business.models.plan.response

data class MealResponse(
    val mealRecipe: List<MealRecipeResponse>,
    val timeOfDay: String
)
