package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.persistence.entities.*
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.*
import com.example.nutrition_service.persistence.tables.*
import com.example.nutrition_service.presentation.http.Response
import org.jetbrains.exposed.sql.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.random.Random


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

    override fun getRecipeByCalories(calories: Int): Response<RecipeLiteModel> {
        return try {
            val result = nutritionDAO.executeQuery {
                val size = Recipes
                    .join(Nutrients, JoinType.INNER, Nutrients.id, Recipes.idNutrients)
                    .select { Nutrients.calories lessEq calories.toFloat() + 50F and
                            (Nutrients.calories greaterEq calories.toFloat() - 50F) }
                    .count()
                Recipes
                    .join(Nutrients, JoinType.INNER, Nutrients.id, Recipes.idNutrients)
                    .select { Nutrients.calories lessEq calories.toFloat() + 50F and
                            (Nutrients.calories greaterEq calories.toFloat() - 50F) }
                    .let { Recipe.wrapRows(it) }
                    .limit(10, Random.nextLong(0, size))
                    .toList()
                    .map {
                        it.toRecipeLiteModel()
                    }
                    .random()
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getRecipes(pag: Int, items: Int): Response<List<RecipeLiteModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                Recipe
                    .all()
                    .limit(items, ((pag - 1) * items).toLong())
                    .toList()
                    .map {
                        it.toRecipeLiteModel()
                    }
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

    override fun createMenu(caloriesThreshold: Int, size: Int): Response<Any> {
        try {
            val threshold = Random.nextInt(caloriesThreshold - 50, caloriesThreshold + 50)
            val result = mutableListOf<Float>()
            result.add(0F)
            result.add(1F)
            result.add(Random.nextDouble(0.2, 0.4).toFloat())
            (1 until size - 1).forEach { _ ->
                var tmp = Random.nextDouble(0.4, 1.0).toFloat()
                while( tmp - result[result.size - 1] < 0.2 || tmp - result[result.size - 1] > 0.4){
                    tmp = Random.nextDouble(0.4, 1.0).toFloat()
                }
                result.add(tmp)
            }
            result.sort()
            val tmp = result
                .subList(1, result.size)
                .mapIndexed { index, it -> it - result[index] }
                .map {
                    it * threshold
                }.map {
                    this.getRecipeByCalories(it.toInt()).data!!
                }
            return Response(successfulOperation = true, data = tmp, code = 200)
        } catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }
}