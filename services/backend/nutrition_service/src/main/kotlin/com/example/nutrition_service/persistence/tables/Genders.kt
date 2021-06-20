package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Genders: IntIdTable() {
    val name: Column<String>        = text("name")
    override val tableName: String  = "genders"
}