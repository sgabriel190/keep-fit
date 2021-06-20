package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UserDetails: IntIdTable() {
    val age: Column<Int>            = integer("age")
    val height: Column<Int>         = integer("height")
    val weight: Column<Int>         = integer("weight")
    val idealWeight: Column<Int>    = integer("ideal_weight")
    val calories: Column<Int>       = integer("calories")
    val bmi: Column<Float>          = float("bmi")
    val wnd: Column<Int>            = integer("wnd")
    val idActivityType              = reference("ID_activity_type", ActivityTypes)
    val idGender                    = reference("ID_gender", Genders)
    val idDietType                  = reference("ID_diet_type", DietTypes)
    override val tableName: String  = "user_details"
}