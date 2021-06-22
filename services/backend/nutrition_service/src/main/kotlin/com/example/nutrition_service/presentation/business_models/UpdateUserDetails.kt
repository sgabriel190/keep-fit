package com.example.nutrition_service.presentation.business_models

data class UpdateUserDetails(
    val age: Int?,
    val height: Int?,
    val weight: Int?,
    val idActivityType: Int?,
    val idDietType: Int?
)
