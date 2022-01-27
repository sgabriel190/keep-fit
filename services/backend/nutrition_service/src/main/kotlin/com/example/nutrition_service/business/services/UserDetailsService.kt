package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.UserDetailsServiceInterface
import com.example.nutrition_service.business.interfaces.UtilsInterface
import com.example.nutrition_service.persistence.entities.*
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.ActivityTypeModel
import com.example.nutrition_service.persistence.pojos.UserDetailModel
import com.example.nutrition_service.presentation.business_models.CreateUserDetails
import com.example.nutrition_service.presentation.business_models.UpdateUserDetails
import com.example.nutrition_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.NullPointerException


@Service
class UserDetailsService: UserDetailsServiceInterface {
    @Autowired
    lateinit var nutritionDAO: NutritionDAOInterface

    @Autowired
    lateinit var utils: UtilsInterface

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
        } catch (e: NullPointerException){
            Response(successfulOperation = false, data = null, code = 404, error = "Record doesn't exist")
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun addUserDetails(data: CreateUserDetails): Response<Any> {
        try {
            val resultActivityType = nutritionDAO.executeQuery {
                ActivityType.findById(data.idActivityType)!!
            }
            val resultGender = nutritionDAO.executeQuery {
                Gender.findById(data.idGender)!!
            }
            val resultDietType = nutritionDAO.executeQuery {
                DietType.findById(data.idDietType)!!
            }
            val idealWeight = this.utils.computeIdealWeight(data.height, data.age, data.idGender)
            val bmi = this.utils.computeBMI(data.weight, data.height)
            val wnd = this.utils.computeWND(idealWeight)
            val calories = this.utils.computeCalories(idealWeight, resultDietType.calories, resultActivityType.calories)
            val userDetailsId = nutritionDAO.executeQuery {
                val result = UserDetail.new{
                    this.age = data.age
                    this.height = data.height
                    this.weight = data.weight
                    this.calories = calories
                    this.bmi = bmi
                    this.wnd = wnd
                    this.idealWeight = idealWeight
                    this.idActivityType = resultActivityType
                    this.idGender = resultGender
                    this.idDietType = resultDietType
                }
                result.id.value
            }
            return Response(data = userDetailsId, code = 201, successfulOperation = true)
        }
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun deleteUserDetails(id: Int): Response<Any> {
        return try {
            nutritionDAO.executeQuery {
                UserDetail.findById(id)!!.delete()
            }
            Response(data = null, code = 204, successfulOperation = true)
        } catch (e: NullPointerException){
            Response(successfulOperation = false, data = null, code = 404, error = "Record doesn't exist")
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun updateUserDetails(id: Int, data: UpdateUserDetails): Response<Any> {
        return try {
            nutritionDAO.executeQuery {
                val userDetail = UserDetail.findById(id) ?: throw Exception("User detail not found.")
                if (data.weight != null)
                    userDetail.weight = data.weight
                if (data.height != null)
                    userDetail.height = data.height
                if (data.age != null)
                    userDetail.age = data.age
                if (data.idActivityType != null)
                    userDetail.idActivityType = ActivityType.findById(data.idActivityType) ?: throw Exception("Activity type not found.")
                if (data.idDietType != null)
                    userDetail.idDietType = DietType.findById(data.idDietType) ?: throw Exception("Diet type not found.")
                if (data.height != null || data.age != null){
                    userDetail.idealWeight = utils.computeIdealWeight(userDetail.height, userDetail.age, userDetail.idGender.id.value)
                    userDetail.wnd = utils.computeWND(userDetail.idealWeight)
                    userDetail.calories = utils.computeCalories(
                        userDetail.idealWeight,
                        userDetail.idDietType.calories,
                        userDetail.idActivityType.calories
                    )
                }
                if (data.weight != null || data.height != null){
                    userDetail.bmi = utils.computeBMI(userDetail.weight, userDetail.height)
                }
            }
            val result = nutritionDAO.executeQuery {
                UserDetail.findById(id)?.toUserDetailModel() ?: throw Exception("User detail not found.")
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getGenders(): Response<Any> {
        return try {
            val result = nutritionDAO.executeQuery {
                Gender.all().map { it.toGenderModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getDietTypes(): Response<Any> {
        return try {
            val result = nutritionDAO.executeQuery {
                DietType.all().map { it.toDietTypeModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }
}