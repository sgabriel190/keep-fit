package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.nutrition.CreateMeal
import com.example.orchestrator_service.business.models.nutrition.CreateUserDetails
import com.example.orchestrator_service.presentation.http.Response

interface NutritionServiceInterface {
    suspend fun getImage(imagePath: String, token: String): Response<out Any>
    suspend fun getRecipes(params: Map<String, Any>, token: String): Response<out Any>
    suspend fun getRecipe(id: Int, token: String): Response<out Any>
    suspend fun getGenders(token: String): Response<out Any>
    suspend fun getCategories(token: String): Response<out Any>
    suspend fun createMeal(data: CreateMeal, token: String): Response<out Any>
    suspend fun getCategory(id: Int, token: String): Response<out Any>
    suspend fun getDietTypes(token: String): Response<out Any>
    suspend fun getActivityTypes(token: String): Response<out Any>
    suspend fun getActivityType(id: Int, token: String): Response<out Any>
    suspend fun getUserDetails(id: Int, token: String): Response<out Any>
    suspend fun addUserDetails(data: CreateUserDetails, token: String): Response<out Any>
}