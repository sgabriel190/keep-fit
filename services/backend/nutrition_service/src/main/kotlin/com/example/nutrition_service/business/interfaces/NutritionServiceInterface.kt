package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.persistence.pojos.*
import com.example.nutrition_service.presentation.http.Response

interface NutritionServiceInterface {
    // Instructions CRUD operations
    fun getInstruction(id: Int): Response<InstructionModel>
    fun getInstructions(idRecipe: Int): Response<List<InstructionModel>>

    // Recipes CRUD operations
    fun getRecipe(id: Int): Response<RecipeModel>
    fun getRecipeByCalories(calories: Int): Response<RecipeLiteModel>
    fun getRecipes(pag: Int = 1, items: Int = 10): Response<List<RecipeLiteModel>>
    fun getRecipeByCategoryId(idCategory: Int, pag: Int, items: Int): Response<List<RecipeLiteModel>>
    fun getRecipeByCategoryName(nameCategory: String, pag: Int, items: Int): Response<List<RecipeLiteModel>>

    // Images CRUD operations
    fun getImages(idRecipe: Int): Response<List<ImageModel>>

    // Categories CRUD operations
    fun getCategories(): Response<List<CategoryModel>>
    fun getCategory(id: Int): Response<CategoryModel>

    fun createMenu(caloriesThreshold: Int, size: Int = 3): Response<Any>
}