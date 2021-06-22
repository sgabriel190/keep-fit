package com.example.orchestrator_service.business.models.nutrition.response

data class RecipeResponse (
    val nutrients: NutrientResponse,
    val timeTotal: TimeTotalResponse,
    val name: String,
    val description: String,
    val keywords: String,
    val categories: List<CategoryResponse>,
    val images: List<ImageResponse>,
    val instructions: List<InstructionResponse>,
    val ingredients: List<IngredientResponse>
)