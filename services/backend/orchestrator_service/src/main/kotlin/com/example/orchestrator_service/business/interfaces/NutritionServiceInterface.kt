package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.business.models.nutrition.request.CreateMealRequest
import com.example.orchestrator_service.business.models.nutrition.request.UpdateUserDetailsRequest
import com.example.orchestrator_service.business.models.nutrition.request.UserDetailsRequest
import com.example.orchestrator_service.business.models.nutrition.response.*
import com.example.orchestrator_service.business.models.nutrition.request.MenuRequest
import com.example.orchestrator_service.presentation.http.Response

interface NutritionServiceInterface {
    suspend fun getImage(imagePath: String, token: String): Response<out Any>
    suspend fun getRecipes(params: Map<String, Any>, token: String): Response<List<RecipeLiteResponse>>
    suspend fun getRecipe(id: Int, token: String): Response<RecipeResponse>
    suspend fun getGenders(token: String): Response<List<GenderResponse>>
    suspend fun getCategories(token: String): Response<List<CategoryResponse>>
    suspend fun createMeal(data: CreateMealRequest, token: String): Response<List<RecipeLiteResponse>>
    suspend fun getCategory(id: Int, token: String): Response<CategoryResponse>
    suspend fun getDietTypes(token: String): Response<List<DietTypeResponse>>
    suspend fun getActivityTypes(token: String): Response<List<ActivityTypeResponse>>
    suspend fun getActivityType(id: Int, token: String): Response<ActivityTypeResponse>
    suspend fun getUserDetails(token: String): Response<UserDetailResponse>
    suspend fun deleteUserDetails(token: String): Response<out Any>
    suspend fun addUserDetails(data: UserDetailsRequest, token: String): Response<Int>
    suspend fun updateUserDetails(data: UpdateUserDetailsRequest, token: String): Response<UserDetailResponse>
    suspend fun getRecipesForMenu(data: MenuRequest, token: String): Response<MenuResponse>
}