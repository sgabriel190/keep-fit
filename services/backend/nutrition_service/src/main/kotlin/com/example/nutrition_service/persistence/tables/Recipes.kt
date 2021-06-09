package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Recipes: IntIdTable() {
    val idNutrients                 = reference("ID_nutrients", Nutrients)
    val timeTotal                   = reference("ID_time_total", TimeTotals)
    val name                        = text("name")
    val description                 = text("description")
    val keywords                    = text("keywords")
    override val tableName: String  = "recipes"
}