package com.example.nutrition_service.persistence.pojos

data class MacronutrientModel(
    val id: Int,
    val carbohydrates: String?,
    val proteins: String?,
    val fats: String?,
    val saturatedFats: String?,
    val fibers: String?,
    val sugars: String?
)
