package com.example.orchestrator_service.business.models.nutrition

data class CreateUserDetails(
    val age: Int,
    val height: Int,
    val weight: Int,
    val idActivityType: Int,
    val idGender: Int,
    val idDietType: Int
)
