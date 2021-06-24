package com.example.nutrition_service.presentation.business_models

data class MenuRequest(
    val menu: List<RecipesMenuRequest>
){
    constructor() : this(menu = listOf()) {

    }
}
