package com.example.orchestrator_service.business.models.plan.request

data class MenuRequest(
    val menu: List<RecipeMenuRequest>
){
    constructor() : this(menu = listOf()) {

    }
}
