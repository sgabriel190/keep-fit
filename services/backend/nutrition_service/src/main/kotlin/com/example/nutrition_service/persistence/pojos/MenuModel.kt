package com.example.nutrition_service.persistence.pojos

data class MenuModel(
    val menu: List<RecipeMenuModel>
){
    constructor(): this(menu = listOf()) {

    }
}
