package com.example.nutrition_service.persistence.repositories

import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.sql.Connection

@Repository
class NutritionDAO: NutritionDAOInterface {
    init {
        Database.connect("jdbc:sqlite:nutrition.db", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }

    override fun <T> executeQuery(block: () -> T): T {
        return transaction {
            addLogger(StdOutSqlLogger)
            block()
        }
    }


}