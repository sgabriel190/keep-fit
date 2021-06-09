package com.example.nutrition_service.persistence.pojos

data class RecipeModel(
    val nutrients: NutrientModel,
    val timeTotal: TimeTotalModel,
    val name: String,
    val description: String,
    val keywords: String,
    val categories: List<CategoryModel>,
    val images: List<ImageModel>?,
    val instructions: List<InstructionModel>?
)
