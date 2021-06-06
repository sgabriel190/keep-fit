package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.persistence.pojos.MacronutrientModel

interface NutritionServiceInterface {
    fun getMacronutrient(id: Int): MacronutrientModel
}