package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.RecipeLiteModel
import com.example.nutrition_service.persistence.pojos.RecipeModel
import com.example.nutrition_service.persistence.tables.Images
import com.example.nutrition_service.persistence.tables.Instructions
import com.example.nutrition_service.persistence.tables.RecipeToCategories
import com.example.nutrition_service.persistence.tables.Recipes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Recipe(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Recipe>(Recipes)
    var name        by Recipes.name
    var description by Recipes.description
    var keywords    by Recipes.keywords
    var categories  by Category via RecipeToCategories
    var timeTotal   by TimeTotal referencedOn Recipes.timeTotal
    var nutrients   by Nutrient referencedOn Recipes.idNutrients
}

fun Recipe.toRecipeModel(): RecipeModel{
    return RecipeModel(
        nutrients = this.nutrients.toNutrientModel(),
        timeTotal = this.timeTotal.toTimeTotalModel(),
        name = this.name,
        description = this.description,
        keywords = this.keywords,
        categories = this.categories.toList().map { it.toCategoryModel() },
        images = null,
        instructions = null
    )
}

fun Recipe.toRecipeLiteModel(): RecipeLiteModel {
    return RecipeLiteModel(
        timeTotal = this.timeTotal.toTimeTotalModel(),
        name = this.name,
        description = this.description,
        keywords = this.keywords,
        categories = this.categories.toList().map { it.toCategoryModel() }
    )
}