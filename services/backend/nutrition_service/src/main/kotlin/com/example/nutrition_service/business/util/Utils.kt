package com.example.nutrition_service.business.util

import com.example.nutrition_service.business.interfaces.UtilsInterface
import com.example.nutrition_service.persistence.entities.Gender
import com.example.nutrition_service.persistence.entities.toGenderModel
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.GenderModel
import com.example.nutrition_service.persistence.repositories.NutritionDAO
import org.springframework.stereotype.Component

@Component
class Utils: UtilsInterface {
    final var nutritionDAO: NutritionDAOInterface = NutritionDAO()

    var genders: List<GenderModel> = nutritionDAO.executeQuery {
        Gender.all().map { it.toGenderModel() }
    }

    override fun computeIdealWeight(height: Int, age: Int, gender: Int): Int {
        var result = 50 + 0.75 * (height - 150) + (age - 20) / 4
        val tmp = genders.find {
            it.id == gender
        }
        if (tmp!!.name == "Female") {
            result *= 0.9
        }
        return result.toInt()
    }

    override fun computeBMI(weight: Int, height: Int): Float {
        val heightMeters = height / 100F
        return weight / (heightMeters * heightMeters)
    }

    override fun computeWND(idealWeight: Int): Int {
        return idealWeight * 35
    }

    override fun computeCalories(idealWeight: Int, dietTypeIndex: Int, activityTypeIndex: Float): Int {
        return (idealWeight * dietTypeIndex * activityTypeIndex).toInt()
    }
}