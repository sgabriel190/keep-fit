package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Images: IntIdTable() {
    val imagePath: Column<String>   = text("image_path")
    val idRecipe                    = reference("ID_recipe", Recipes)
    override val tableName: String  = "images"
}