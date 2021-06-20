package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Ingredients: IntIdTable() {
    val ingredient: Column<String>  = text("name")
    val idRecipe                    = reference("ID_recipe", Recipes)
    override val tableName: String  = "ingredients"
}