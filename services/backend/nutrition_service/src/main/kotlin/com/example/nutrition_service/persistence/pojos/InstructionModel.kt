package com.example.nutrition_service.persistence.pojos

data class InstructionModel(
    val id: Int,
    val instruction: String,
    val idRecipe: Int
)