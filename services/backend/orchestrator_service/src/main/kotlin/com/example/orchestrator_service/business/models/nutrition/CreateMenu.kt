package com.example.orchestrator_service.business.models.nutrition

data class CreateMenu(
    val calories: Int
){
    constructor(): this(calories = 0) {

    }
}
