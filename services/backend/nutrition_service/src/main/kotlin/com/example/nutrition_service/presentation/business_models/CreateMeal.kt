package com.example.nutrition_service.presentation.business_models

data class CreateMenu(
    val calories: Int
){
    constructor(): this(calories = 0) {

    }
}
