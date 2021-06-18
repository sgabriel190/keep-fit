package com.example.nutrition_service.persistence.pojos

data class NutrientModel(
    val calories: Float,
    val macronutrients: MacronutrientModel
)
