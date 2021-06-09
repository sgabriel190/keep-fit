package com.example.nutrition_service.persistence.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Macronutrients: IntIdTable() {
    val carbohydrates                   = text("carbohydrates").nullable()
    val proteins                        = text("proteins").nullable()
    val fats                            = text("fats").nullable()
    val saturatedFats                   = text("saturated_fats").nullable()
    val fibers                          = text("fibers").nullable()
    val sugars                          = text("sugars").nullable()
    override val tableName: String      = "macronutrients"
}