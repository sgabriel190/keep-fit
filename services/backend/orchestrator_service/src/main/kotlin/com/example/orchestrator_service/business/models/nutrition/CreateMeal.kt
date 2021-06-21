package com.example.orchestrator_service.business.models.nutrition

data class CreateMeal(
    val calories: Int,
    val size: Int
){
    constructor(): this(calories = 0, size = 0) {

    }
}
