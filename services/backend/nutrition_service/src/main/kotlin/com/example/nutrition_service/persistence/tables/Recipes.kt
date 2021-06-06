package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Recipes: IntIdTable() {
    val idNutrients = reference("ID", Nutrients)
    val idTimeTotal = reference("ID", TimeTotal)
    val name: Column<String> = text("name")
    val description: Column<String> = text("description")
    val keywords: Column<String> = text("keywords")
}