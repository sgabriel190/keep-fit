package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TimeTotal: IntIdTable() {
    val prepTime: Column<String> = text("prep_time")
    val cookTime: Column<String> = text("cook_time")
    val totalTime: Column<String> = text("total_time")
}