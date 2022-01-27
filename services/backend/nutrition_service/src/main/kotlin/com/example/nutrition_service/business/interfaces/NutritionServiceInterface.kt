package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.persistence.pojos.*
import com.example.nutrition_service.presentation.business_models.CreateMeal
import com.example.nutrition_service.presentation.business_models.MenuRequest
import com.example.nutrition_service.presentation.http.Response

interface NutritionServiceInterface {
    // Recipes CRUD operations
    fun getRecipe(id: Int): Response<RecipeModel>
    fun getRecipeByCalories(calories: Int, size: Int, window: Float = 25F): Response<List<RecipeLiteModel>>
    fun getRecipes(pag: Int = 1, items: Int = 10): Response<List<RecipeLiteModel>>
    fun getRecipeByCategoryId(idCategory: Int, pag: Int, items: Int): Response<List<RecipeLiteModel>>
    fun getRecipeByName(recipeName: String, pag: Int, items: Int): Response<List<RecipeLiteModel>>
    fun getRecipeByNameAndCategory(recipeName: String, categoryName: String, pag: Int, items: Int): Response<List<RecipeLiteModel>>
    fun getRecipeByCategoryName(nameCategory: String, pag: Int, items: Int): Response<List<RecipeLiteModel>>

    // Images CRUD operations
    fun getImages(idRecipe: Int): Response<List<ImageModel>>

    // Categories CRUD operations
    fun getCategories(): Response<List<CategoryModel>>
    fun getCategory(id: Int): Response<CategoryModel>

    fun createMeal(data: CreateMeal): Response<Any>
    fun getMenuRecipes(data: MenuRequest): Response<MenuModel>
}