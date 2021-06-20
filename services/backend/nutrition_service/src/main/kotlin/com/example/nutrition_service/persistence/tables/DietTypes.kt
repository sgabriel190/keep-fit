package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object DietTypes: IntIdTable()  {
    val name: Column<String>        = text("name")
    val calories: Column<Int>       = integer("calories")
    override val tableName: String  = "diet_types"
}