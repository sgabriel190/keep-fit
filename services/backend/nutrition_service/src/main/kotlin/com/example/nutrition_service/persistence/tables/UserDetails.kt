package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UserDetails: IntIdTable() {
    val age: Column<Int> = integer("age")
    val height: Column<Int> = integer("height")
    val weight: Column<Int> = integer("weight")
    val calories: Column<Int> = integer("calories")
    val bmi: Column<Int> = integer("bmi")
    val idActivityType = reference("ID", ActivityTypes)
}