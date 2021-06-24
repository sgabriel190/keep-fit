package com.example.orchestrator_service.business.models.nutrition.request

data class UpdateUserDetailsRequest(
    val age: Int?,
    val height: Int?,
    val weight: Int?,
    val idActivityType: Int?,
    val idDietType: Int?
)
