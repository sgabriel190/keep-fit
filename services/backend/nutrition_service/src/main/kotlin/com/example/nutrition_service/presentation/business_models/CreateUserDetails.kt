package com.example.nutrition_service.presentation.business_models

data class CreateUserDetails(
    val id: Int,
    val age: Int,
    val height: Int,
    val weight: Int,
    val calories: Int,
    val bmi: Int,
    val idActivityType: Int
)
