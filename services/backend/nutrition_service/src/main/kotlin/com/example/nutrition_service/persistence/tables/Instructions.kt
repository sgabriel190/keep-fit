package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Instructions: IntIdTable() {
    val instruction: Column<String> = text("instruction")
    val idRecipe                    = reference("ID_recipe", Recipes)
    override val tableName          = "instructions"
}