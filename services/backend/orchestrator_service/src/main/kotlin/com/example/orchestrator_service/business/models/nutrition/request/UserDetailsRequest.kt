package com.example.orchestrator_service.business.models.nutrition.request

data class UserDetailsRequest(
    val age: Int,
    val height: Int,
    val weight: Int,
    val idActivityType: Int,
    val idGender: Int,
    val idDietType: Int
)
