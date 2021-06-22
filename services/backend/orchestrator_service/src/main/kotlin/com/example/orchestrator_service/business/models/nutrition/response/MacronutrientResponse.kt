package com.example.orchestrator_service.business.models.nutrition.response

data class MacronutrientResponse(
    val carbohydrates: String,
    val proteins: String,
    val fats: String,
    val saturatedFats: String,
    val fibers: String,
    val sugars: String
)