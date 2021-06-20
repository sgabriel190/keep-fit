package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.consumers.NutritionConsumerService
import com.example.orchestrator_service.business.consumers.UserConsumerService
import com.example.orchestrator_service.business.interfaces.OrchestratorServiceInterface
import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrchestratorService: OrchestratorServiceInterface {
    @Autowired
    lateinit var userConsumerService: UserConsumerService

    @Autowired
    lateinit var nutritionConsumerService: NutritionConsumerService

    override suspend fun authenticateUser(data: LoginRequest): Response<Any> {
        return try {
            userConsumerService.post("auth/login", body = data)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun registerUser(data: RegisterRequest): Response<Any> {
        return try {
            userConsumerService.put("auth/register", body = data)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getImage(imagePath: String): Response<Any> {
        return try {
            val result = nutritionConsumerService.getImage("image/$imagePath")
            Response(successfulOperation = true, code = 200, data = result)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getRecipes(params: Map<String, Any>): Response<Any> {
        return try {
            nutritionConsumerService.get("recipe", reqParam = params)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getGenders(): Response<Any> {
        return try {
            nutritionConsumerService.get("userDetails/gender")
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getDietTypes(): Response<Any> {
        return try {
            nutritionConsumerService.get("userDetails/dietType")
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getActivityTypes(): Response<Any> {
        return try {
            nutritionConsumerService.get("userDetails/activityType")
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getActivityType(id: Int): Response<Any> {
        return try {
            nutritionConsumerService.get("userDetails/activityType/$id")
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

    override suspend fun getUserDetails(id: Int): Response<Any> {
        return try {
            nutritionConsumerService.get("userDetails/$id")
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null)
        }
    }

}