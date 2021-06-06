package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.sql.Table

object RecipeToCategories: Table() {
    val idRecipe = reference("ID", Recipes).uniqueIndex()
    val idCategory = reference("ID", Categories).uniqueIndex()
}