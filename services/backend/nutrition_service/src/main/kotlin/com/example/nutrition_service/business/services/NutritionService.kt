package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.persistence.entities.*
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.*
import com.example.nutrition_service.persistence.tables.*
import com.example.nutrition_service.presentation.http.Response
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class NutritionService: NutritionServiceInterface {
    @Autowired
    lateinit var nutritionDAO: NutritionDAOInterface

    override fun getMacronutrient(id: Int): Response<MacronutrientModel> {
        val result = nutritionDAO.executeQuery {
            Macronutrient.findById(id)!!.toMacronutrientModel()
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getInstruction(id: Int): Response<InstructionModel> {
        val result = nutritionDAO.executeQuery {
            Instruction.findById(id)!!.toInstructionModel()
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getInstructions(idRecipe: Int): Response<List<InstructionModel>> {
        val result = nutritionDAO.executeQuery {
            Instruction.find { Instructions.idRecipe eq idRecipe }.toList().map { it.toInstructionModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getRecipe(id: Int): Response<RecipeModel> {
        val result = nutritionDAO.executeQuery {
            Recipe.findById(id)!!.toRecipeModel()
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getRecipes(pag: Int, items: Int): Response<List<RecipeModel>> {
        val result = nutritionDAO.executeQuery {
            Recipe.all().limit(items, ((pag - 1)  * items).toLong()).toList().map { it.toRecipeModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getRecipeByCategoryId(idCategory: Int): Response<List<RecipeModel>> {
        TODO("Not yet implemented")
    }

    override fun getRecipeByCategoryName(idName: String): Response<List<RecipeModel>> {
        TODO("Not yet implemented")
    }

    override fun getImages(idRecipe: Int): Response<List<ImageModel>> {
        val result = nutritionDAO.executeQuery {
            Image.find { Images.idRecipe eq idRecipe }.toList().map { it.toImageModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getCategories(): Response<List<CategoryModel>> {
        val result = nutritionDAO.executeQuery {
            Category.all().toList().map { it.toCategoryModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getCategory(id: Int): Response<CategoryModel> {
        val result = nutritionDAO.executeQuery {
            Category.findById(id)!!.toCategoryModel()
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }
}