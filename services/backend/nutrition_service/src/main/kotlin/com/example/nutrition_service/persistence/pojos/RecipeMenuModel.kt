package com.example.nutrition_service.persistence.pojos

data class RecipeMenuModel(
    val recipes: List<RecipeLiteModel>,
    val meal: String
)