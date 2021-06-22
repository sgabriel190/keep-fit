package com.example.orchestrator_service.business.models.nutrition.response

data class NutrientResponse(
    val calories: Float,
    val macronutrients: MacronutrientResponse
)