package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.IngredientModel
import com.example.nutrition_service.persistence.tables.Ingredients
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Ingredient(id: EntityID<Int>): IntEntity(id)  {
    companion object: IntEntityClass<Ingredient>(Ingredients)
    var ingredient by Ingredients.ingredient
    var idRecipe by Ingredients.idRecipe
}

fun Ingredient.toIngredientModel() = IngredientModel(
    ingredient = this.ingredient
)