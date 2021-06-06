package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Categories: IntIdTable() {
    val category: Column<String> = text("category")
}