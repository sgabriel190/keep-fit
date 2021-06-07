package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.UserDetailsServiceInterface
import com.example.nutrition_service.persistence.entities.ActivityType
import com.example.nutrition_service.persistence.entities.toActivityTypeModel
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.ActivityTypeModel
import com.example.nutrition_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDetailsService: UserDetailsServiceInterface {
    @Autowired
    lateinit var nutritionDAO: NutritionDAOInterface

    override fun getActivityTypes(): Response<List<ActivityTypeModel>> {
        val result = nutritionDAO.executeQuery {
            ActivityType.all().toList().map { it.toActivityTypeModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getActivityType(id: Int): Response<ActivityTypeModel> {
        val result = nutritionDAO.executeQuery {
            ActivityType.findById(id)!!.toActivityTypeModel()
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }
}