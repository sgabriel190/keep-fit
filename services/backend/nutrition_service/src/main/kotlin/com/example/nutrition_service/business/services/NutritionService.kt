package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.business.interfaces.UtilsInterface
import com.example.nutrition_service.persistence.entities.*
import com.example.nutrition_service.persistence.interfaces.NutritionDAOInterface
import com.example.nutrition_service.persistence.pojos.*
import com.example.nutrition_service.persistence.tables.*
import com.example.nutrition_service.presentation.business_models.CreateMeal
import com.example.nutrition_service.presentation.business_models.MenuRequest
import com.example.nutrition_service.presentation.http.Response
import org.jetbrains.exposed.sql.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.random.Random


@Service
class NutritionService: NutritionServiceInterface {
    @Autowired
    lateinit var nutritionDAO: NutritionDAOInterface

    // Recipes CRUD operations
    override fun getRecipe(id: Int): Response<RecipeModel> {
        try {
            val recipeResult = nutritionDAO.executeQuery {
                Recipe.findById(id)!!.toRecipeModel()
            }
            val imageResult = nutritionDAO.executeQuery {
                Image.find { Images.recipe eq id }.toList().map { it.toImageModel() }
            }
            val instructionResult = nutritionDAO.executeQuery {
                Instruction.find { Instructions.idRecipe eq id }.toList().map { it.toInstructionModel() }
            }
            val ingredientsResult = nutritionDAO.executeQuery {
                Ingredient.find { Ingredients.idRecipe eq id }.toList().map { it.toIngredientModel() }
            }
            val result = RecipeModel(
                nutrients = recipeResult.nutrients,
                timeTotal = recipeResult.timeTotal,
                name = recipeResult.name,
                description = recipeResult.description,
                keywords = recipeResult.keywords,
                categories = recipeResult.categories,
                images = imageResult,
                instructions = instructionResult,
                ingredients = ingredientsResult
            )
            return Response(data = result, code = 200, successfulOperation = true)
        }
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getRecipeByCalories(calories: Int, size: Int, window: Float): Response<List<RecipeLiteModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                val sizeRecipes = Recipes
                    .join(Nutrients, JoinType.INNER, Nutrients.id, Recipes.idNutrients)
                    .select { Nutrients.calories lessEq calories.toFloat() + window and
                            (Nutrients.calories greaterEq calories.toFloat() - window) }
                    .count()
                Recipes
                    .join(Nutrients, JoinType.INNER, Nutrients.id, Recipes.idNutrients)
                    .select { Nutrients.calories lessEq calories.toFloat() + window and
                            (Nutrients.calories greaterEq calories.toFloat() - window) }
                    .let { Recipe.wrapRows(it) }
                    .limit(size, Random.nextLong(0, sizeRecipes))
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

    override fun getRecipeByName(recipeName: String, pag: Int, items: Int): Response<List<RecipeLiteModel>> {
        return try {
            val result = nutritionDAO.executeQuery {
                val regex = "%" + recipeName.split(" ").map{
                    it.lowercase()
                }.reduce{
                    a, b -> "$a%$b%"
                }
                Recipe.find { Recipes.name.lowerCase().like(regex) or( Recipes.keywords.lowerCase().like(regex)) }
                    .limit(items, ((pag - 1)  * items).toLong())
                    .map {
                        it.toRecipeLiteModel()
                    }
            }
            Response(successfulOperation = true, code = 200, data = result)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
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
                Image.find { Images.recipe eq idRecipe }.toList().map { it.toImageModel() }
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

    override fun createMeal(data: CreateMeal): Response<Any> {
        try {
            val threshold = data.calories
            if(data.size >= 2){
                val result = mutableListOf<Float>()
                val step = (1F/data.size).toDouble()
                val window = step / 2
                result.add(0F)
                result.add(1F)
                (1 until data.size).forEach { idx ->
                    val tmp = Random.nextDouble((idx * step) - window, (idx * step) + window).toFloat()
                    result.add(tmp)
                }
                result.sort()
                val tmp = result
                    .subList(1, result.size)
                    .mapIndexed { index, it -> it - result[index] }
                    .map {
                        it * threshold
                    }.map {
                        this.getRecipeByCalories(it.toInt(), 1, window = 10F).data?.random()
                    }
                return Response(successfulOperation = true, data = tmp, code = 200)
            } else {
                if (data.size <= 0) {
                    throw Exception("Incorrect number of recipes in a meal.")
                }
                val result = listOf(this.getRecipeByCalories(threshold, 1).data?.random())
                return Response(successfulOperation = true, data = result, code = 200)
            }

        } catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun getMenuRecipes(data: MenuRequest): Response<MenuModel> {
        return try {
            val result = nutritionDAO.executeQuery {
                val tmp = data.menu.map { menu ->
                    val tmp = menu.recipes.map { recipe ->
                        Recipe.findById(recipe)!!.toRecipeLiteModel()
                    }
                    RecipeMenuModel(recipes = tmp, meal = menu.meal)
                }
                MenuModel(tmp)
            }
            Response(data = result, code = 200, successfulOperation = true)
        } catch (t: Throwable){
            Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }
}