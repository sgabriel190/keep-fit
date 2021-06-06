package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.RecipeModel
import com.example.nutrition_service.persistence.tables.Recipes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Recipe(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Recipe>(Recipes)
    var idNutrients by Recipes.idNutrients
    var idTimeTotal by Recipes.idTimeTotal
    var name        by Recipes.name
    var description by Recipes.description
    var keywords    by Recipes.keywords
}

fun Recipe.toRecipeModel(): RecipeModel{
    return RecipeModel(
        id = this.id.value,
        idNutrients = this.idNutrients.value,
        idTimeTotal = this.idTimeTotal.value,
        name = this.name,
        description = this.description,
        keywords = this.keywords
    )
}