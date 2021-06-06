package com.example.nutrition_service.persistence.pojos

data class RecipeModel(
    val id: Int,
    val idNutrients: Int,
    val idTimeTotal: Int?,
    val name: String,
    val description: String,
    val keywords: String
)
