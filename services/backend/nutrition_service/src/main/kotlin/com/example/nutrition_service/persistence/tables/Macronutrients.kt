package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Macronutrients: IntIdTable() {
    val carbohydrates: Column<String> = text("carbohydrates")
    val proteins: Column<String> = text("proteins")
    val fats: Column<String> = text("fats")
    val saturatedFats: Column<String> = text("saturated_fats")
    val fibers: Column<String> = text("fibers")
    val sugars: Column<String> = text("sugars")
    override val tableName: String = "macronutrients"
}