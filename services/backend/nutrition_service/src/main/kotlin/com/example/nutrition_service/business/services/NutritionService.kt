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

    // Instructions CRUD operations
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

    // Recipes CRUD operations
    override fun getRecipe(id: Int): Response<RecipeModel> {
        val recipeResult = nutritionDAO.executeQuery {
            Recipe.findById(id)!!.toRecipeModel()
        }
        val imageResult = nutritionDAO.executeQuery {
            Image.find { Images.idRecipe eq id }.toList().map { it.toImageModel() }
        }
        val instructionResult = nutritionDAO.executeQuery {
            Instruction.find { Instructions.idRecipe eq id }.toList().map { it.toInstructionModel() }
        }
        val result = RecipeModel(
            nutrients = recipeResult.nutrients,
            timeTotal = recipeResult.timeTotal,
            name = recipeResult.name,
            description = recipeResult.description,
            keywords = recipeResult.keywords,
            categories = recipeResult.categories,
            images = imageResult,
            instructions = instructionResult
        )
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getRecipes(pag: Int, items: Int): Response<List<RecipeLiteModel>> {
        val result = nutritionDAO.executeQuery {
            Recipe.all().limit(items, ((pag - 1)  * items).toLong()).toList().map { it.toRecipeLiteModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    override fun getRecipeByCategoryId(idCategory: Int): Response<List<RecipeLiteModel>> {
        /*
        val resultCategory = nutritionDAO.executeQuery {
            Category.findById(idCategory)!!.toCategoryModel()
        }
        val recipeCategory = nutritionDAO.executeQuery {

        }
        return Response(data = result, code = 200, successfulOperation = true)
        */
        TODO("Not yet implemented")
    }

    override fun getRecipeByCategoryName(idName: String): Response<List<RecipeLiteModel>> {
        TODO("Not yet implemented")
    }

    // Images CRUD operations
    override fun getImages(idRecipe: Int): Response<List<ImageModel>> {
        val result = nutritionDAO.executeQuery {
            Image.find { Images.idRecipe eq idRecipe }.toList().map { it.toImageModel() }
        }
        return Response(data = result, code = 200, successfulOperation = true)
    }

    // Categories CRUD operations
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