package com.example.nutrition_service.persistence.pojos

data class UserDetailModel(
    val age: Int,
    val height: Int,
    val weight: Int,
    val calories: Int,
    val bmi: Int,
    val idActivityType: ActivityTypeModel
)
