package com.example.nutrition_service.persistence.repositories

import com.example.nutrition_service.persistence.tables.Macronutrients
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

class NutritionDAO {
    init {
        Database.connect("jdbc:sqlite:nutrition.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }

    fun getMacronutrientsById(id: Int) {
        transaction {
            addLogger(StdOutSqlLogger)
            val queryResult = Macronutrients.select{ Macronutrients.id eq id}
            queryResult.forEach {
                print(it)
            }
        }
    }
}