package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TimeTotals: IntIdTable() {
    val prepTime: Column<String?>    = text("prep_time").nullable()
    val cookTime: Column<String?>    = text("cook_time").nullable()
    val totalTime: Column<String?>   = text("total_time").nullable()
    override val tableName: String  = "time_total"
}