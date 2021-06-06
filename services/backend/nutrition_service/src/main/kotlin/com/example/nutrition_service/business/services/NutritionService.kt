package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.persistence.entities.Macronutrient
import com.example.nutrition_service.persistence.entities.toMacronutrientModel
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.MacronutrientModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class NutritionService: NutritionServiceInterface {
    @Autowired
    lateinit var nutritionDAO: NutritionDAOInterface

    override fun getMacronutrient(id: Int): MacronutrientModel {
        val result = nutritionDAO.executeQuery {
            Macronutrient.findById(id)!!.toMacronutrientModel()
        }
        return result
    }
}