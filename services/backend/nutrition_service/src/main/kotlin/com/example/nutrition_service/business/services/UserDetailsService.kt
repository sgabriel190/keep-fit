package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.UserDetailsServiceInterface
import com.example.nutrition_service.persistence.entities.ActivityType
import com.example.nutrition_service.persistence.entities.UserDetail
import com.example.nutrition_service.persistence.entities.toActivityTypeModel
import com.example.nutrition_service.persistence.entities.toUserDetailModel
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.ActivityTypeModel
import com.example.nutrition_service.persistence.pojos.UserDetailModel
import com.example.nutrition_service.presentation.business_models.CreateUserDetails
import com.example.nutrition_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDetailsService: UserDetailsServiceInterface {
    @Autowired
    lateinit var nutritionDAO: NutritionDAOInterface

    override fun getActivityTypes(): Response<List<ActivityTypeModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                ActivityType.all().toList().map { it.toActivityTypeModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getActivityType(id: Int): Response<ActivityTypeModel> {
        return try {
            val result = nutritionDAO.executeQuery {
                ActivityType.findById(id)!!.toActivityTypeModel()
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getUserDetails(id: Int): Response<UserDetailModel> {
        return try {
            val result = nutritionDAO.executeQuery {
                UserDetail.findById(id)!!.toUserDetailModel()
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun addUserDetails(data: CreateUserDetails): Response<Any> {
        try {
            val resultActivityType = nutritionDAO.executeQuery {
                ActivityType.findById(data.idActivityType)!!
            }
            nutritionDAO.executeQuery {
                UserDetail.new(data.id) {
                    this.age = data.age
                    this.height = data.height
                    this.weight = data.weight
                    this.calories = data.calories
                    this.bmi = data.bmi
                    this.idActivityType = resultActivityType
                }
            }
            return Response(data = null, code = 201, successfulOperation = true)
        }
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }
}