package com.example.orchestrator_service.business.models.nutrition.request

data class CreateMealRequest(
    val calories: Int,
    val size: Int
){
    constructor(): this(calories = 0, size = 0) {

    }
}
