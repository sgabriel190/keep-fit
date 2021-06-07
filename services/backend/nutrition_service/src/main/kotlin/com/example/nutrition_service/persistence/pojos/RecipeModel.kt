package com.example.nutrition_service.persistence.pojos

data class RecipeModel(
    val id: Int,
    val nutrients: NutrientModel,
    val timeTotal: TimeTotalModel,
    val name: String,
    val description: String,
    val keywords: String,
    val categories: List<CategoryModel>
)
