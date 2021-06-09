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
        return try {
            val result = nutritionDAO.executeQuery {
                Instruction.findById(id)!!.toInstructionModel()
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getInstructions(idRecipe: Int): Response<List<InstructionModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                Instruction.find { Instructions.idRecipe eq idRecipe }.toList().map { it.toInstructionModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    // Recipes CRUD operations
    override fun getRecipe(id: Int): Response<RecipeModel> {
        try {
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
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getRecipes(pag: Int, items: Int): Response<List<RecipeLiteModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                Recipe.all().limit(items, ((pag - 1) * items).toLong()).toList().map { it.toRecipeLiteModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getRecipeByCategoryId(idCategory: Int, pag: Int, items: Int): Response<List<RecipeLiteModel>> {
        try {
            val result = nutritionDAO.executeQuery {
                Recipes.innerJoin(RecipeToCategories)
                    .select { RecipeToCategories.idCategory eq idCategory }
                    .limit(items, ((pag - 1) * items).toLong())
                    .let { Recipe.wrapRows(it) }
                    .toList()
                    .map {
                        it.toRecipeLiteModel()
                    }
            }
            return Response(data = result, code = 200, successfulOperation = true)
        }
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getRecipeByCategoryName(nameCategory: String, pag: Int, items: Int): Response<List<RecipeLiteModel>> {
        try {
            val result = nutritionDAO.executeQuery {
                Recipes.innerJoin(RecipeToCategories)
                    .innerJoin(Categories)
                    .select { RecipeToCategories.idCategory eq Categories.id and(Categories.category eq nameCategory)}
                    .limit(items, ((pag - 1)  * items).toLong())
                    .let { Recipe.wrapRows(it) }
                    .toList()
                    .map {
                        it.toRecipeLiteModel()
                    }
            }
            return Response(data = result, code = 200, successfulOperation = true)
        }
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    // Images CRUD operations
    override fun getImages(idRecipe: Int): Response<List<ImageModel>> {
        return try{
            val result = nutritionDAO.executeQuery {
                Image.find { Images.idRecipe eq idRecipe }.toList().map { it.toImageModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    // Categories CRUD operations
    override fun getCategories(): Response<List<CategoryModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                Category.all().toList().map { it.toCategoryModel() }
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getCategory(id: Int): Response<CategoryModel> {
        return try {
            val result = nutritionDAO.executeQuery {
                Category.findById(id)!!.toCategoryModel()
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }
}