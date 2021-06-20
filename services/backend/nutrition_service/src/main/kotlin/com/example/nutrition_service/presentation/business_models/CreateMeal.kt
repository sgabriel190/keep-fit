package com.example.nutrition_service.presentation.business_models

data class CreateMeal(
    val calories: Int,
    val size: Int
){
    constructor(): this(calories = 0, size = 0) {

    }
}
