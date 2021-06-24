package com.example.orchestrator_service.business.models.nutrition.response

data class MenuResponse(
    val menu: List<RecipeMenuResponse>
){
    constructor(): this(menu = listOf()) {

    }
}
