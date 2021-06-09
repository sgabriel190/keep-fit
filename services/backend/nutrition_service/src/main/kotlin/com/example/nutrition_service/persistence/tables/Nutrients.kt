package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Nutrients: IntIdTable() {
    val calories: Column<String> = text("calories")
    val idMacronutrients = reference("ID_macronutrients", Macronutrients)
    override val tableName: String = "nutrients"
}