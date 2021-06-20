package com.example.nutrition_service.presentation.business_models

data class CreateUserDetails(
    val age: Int,
    val height: Int,
    val weight: Int,
    val idActivityType: Int,
    val idGender: Int,
    val idDietType: Int
)
