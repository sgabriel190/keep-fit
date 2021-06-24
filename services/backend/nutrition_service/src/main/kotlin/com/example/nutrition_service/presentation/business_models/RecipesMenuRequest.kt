package com.example.nutrition_service.presentation.business_models

data class RecipesMenuRequest(
    val recipes: List<Int>,
    val meal: String
)
