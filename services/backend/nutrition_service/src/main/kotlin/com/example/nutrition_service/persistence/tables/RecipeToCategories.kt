package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.sql.Table

object RecipeToCategories: Table() {
    val idRecipe                    = reference("ID_recipe", Recipes)
    val idCategory                  = reference("ID_category", Categories)
    override val tableName: String  = "recipe_to_category"
}