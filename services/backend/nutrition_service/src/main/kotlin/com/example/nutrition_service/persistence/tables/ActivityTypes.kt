package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object ActivityTypes: IntIdTable() {
    val name: Column<String> = text("name")
}