package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.nutrition.CreateMeal
import com.example.orchestrator_service.business.models.nutrition.CreateUserDetails
import com.example.orchestrator_service.presentation.http.Response

interface NutritionServiceInterface {
    suspend fun getImage(imagePath: String): Response<Any>
    suspend fun getRecipes(params: Map<String, Any>): Response<Any>
    suspend fun getRecipe(id: Int): Response<Any>
    suspend fun getGenders(): Response<Any>
    suspend fun getCategories(): Response<Any>
    suspend fun createMeal(data: CreateMeal): Response<Any>
    suspend fun getCategory(id: Int): Response<Any>
    suspend fun getDietTypes(): Response<Any>
    suspend fun getActivityTypes(): Response<Any>
    suspend fun getActivityType(id: Int): Response<Any>
    suspend fun getUserDetails(id: Int): Response<Any>
    suspend fun addUserDetails(data: CreateUserDetails): Response<Any>
}