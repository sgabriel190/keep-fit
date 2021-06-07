package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.persistence.pojos.*
import com.example.nutrition_service.presentation.http.Response

interface NutritionServiceInterface {
    fun getMacronutrient(id: Int): Response<MacronutrientModel>

    fun getInstruction(id: Int): Response<InstructionModel>
    fun getInstructions(idRecipe: Int): Response<List<InstructionModel>>

    fun getRecipe(id: Int): Response<RecipeModel>
    fun getRecipes(pag: Int = 1, items: Int = 10): Response<List<RecipeModel>>
    fun getRecipeByCategoryId(idCategory: Int): Response<List<RecipeModel>>
    fun getRecipeByCategoryName(idName: String): Response<List<RecipeModel>>

    fun getImages(idRecipe: Int): Response<List<ImageModel>>

    fun getCategories(): Response<List<CategoryModel>>
    fun getCategory(id: Int): Response<CategoryModel>
}