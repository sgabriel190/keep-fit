package com.example.orchestrator_service.business.models.nutrition

data class CreateUserDetails(
    val id: Int,
    val age: Int,
    val height: Int,
    val weight: Int,
    val calories: Int,
    val bmi: Int,
    val idActivityType: Int
)
