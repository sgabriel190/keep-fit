package com.example.nutrition_service.persistence.interfaces

interface NutritionDAOInterface {
    fun <T> executeQuery(block: () -> T): T
}