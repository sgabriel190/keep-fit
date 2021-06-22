package com.example.orchestrator_service.business.models.plan.response

data class MealModelResponse(
    val mealRecipe: List<MealRecipeModelResponse>,
    val timeOfDay: String
)
