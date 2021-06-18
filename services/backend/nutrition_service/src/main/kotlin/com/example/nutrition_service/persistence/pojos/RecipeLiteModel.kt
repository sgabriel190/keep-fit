package com.example.nutrition_service.persistence.pojos

data class RecipeLiteModel(
    val id: Int,
    val timeTotal: TimeTotalModel,
    val name: String,
    val description: String,
    val keywords: String,
    val categories: List<CategoryModel>
)