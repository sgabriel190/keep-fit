package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.exceptions.InvalidJwt
import com.example.orchestrator_service.business.config.exceptions.NoUserDetails
import com.example.orchestrator_service.business.config.exceptions.NoUserPlan
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.nutrition.request.CreateMealRequest
import com.example.orchestrator_service.business.models.nutrition.request.MenuRequest
import com.example.orchestrator_service.business.models.nutrition.request.RecipeMenuRequest
import com.example.orchestrator_service.business.models.nutrition.response.RecipeLiteResponse
import com.example.orchestrator_service.business.models.nutrition.response.UserDetailResponse
import com.example.orchestrator_service.business.models.plan.request.CreateUserPlanRequest
import com.example.orchestrator_service.business.models.plan.request.DailyMenuRequest
import com.example.orchestrator_service.business.models.plan.request.RecipeRequest
import com.example.orchestrator_service.business.models.plan.request.UserPlanRequest
import com.example.orchestrator_service.business.models.plan.response.*
import com.example.orchestrator_service.business.models.user.response.UserModel
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanService: PlanServiceInterface {

    private val host = Host(
        host = "http://${System.getenv("HOST_PLAN") ?: "localhost"}",
        port = "8080",
        path = "api/planning"
    )

    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    @Autowired
    lateinit var userService: UserServiceInterface

    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    private suspend fun checkToken(token: String) {
        val responseValidToken = userService.validateToken(token)
        if (responseValidToken.code / 100 != 2){
            throw InvalidJwt("Invalid jwt.")
        }
    }

    override suspend fun getUserPlan(token: String): Response<PlanResponse> = coroutineScope{
        try {
            checkToken(token)
            val user = userService.getUser(token)
            user.data ?: throw Exception(user.error)
            val result: Response<PlanModelResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/plan/user/${user.data.id}")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result.data ?: throw NoUserPlan("No user plan was found.")
            val menus = result.data.menus.map { menu ->
                val meals = mutableListOf<RecipeMenuRequest>()
                menu.meals.forEach{ meal ->
                    val recipesId = meal.mealRecipe.map { recipe ->
                        recipe.idRecipe
                    }
                    meals.add(RecipeMenuRequest(recipesId, meal.timeOfDay))
                }
                val recipesResult = nutritionService.getRecipesForMenu(MenuRequest(meals.toList()), token)
                recipesResult.data ?: throw Exception("Can't find recipes for menu")
                val tmpMenu = recipesResult.data.menu.map { menuToken ->
                    val tmpRecipes = menuToken.recipes.map { recipe ->
                        MealRecipeResponse(recipe)
                    }
                    MealResponse(mealRecipe = tmpRecipes, timeOfDay = menuToken.meal)
                }
                MenuResponse(meals = tmpMenu, day = menu.day)
            }
            val data = PlanResponse(idUser = result.data.idUser, planDays = result.data.planDays, description = result.data.description, menus = menus)
            Response(successfulOperation = true, code = 200, data = data)
        }
        catch (e: NoUserPlan){
            Response(
                successfulOperation = false,
                code = 404,
                data = null,
                error = e.message!!
            )
        }
        catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 401,
                data = null,
                error = e.toString()
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = null,
                error = t.toString()
            )
        }
    }

    override suspend fun createUserPlan(data: CreateUserPlanRequest, token: String): Response<PlanModelResponse> = coroutineScope{
        try {
            val userResponse: Response<UserModel> = userService.getUser(token)
            val user: UserModel = userResponse.data ?: throw Exception(userResponse.error)
            deleteUserPlan(token)
            val userDetailsResponse = nutritionService.getUserDetails(token)
            if (userDetailsResponse.code == 404) throw NoUserDetails(userDetailsResponse.error)
            val userDetails: UserDetailResponse = userDetailsResponse.data ?: throw Exception(userDetailsResponse.error)
            val menus = mutableListOf<DailyMenuRequest>()
            for (day in 1..data.planDays) coroutineScope {
                val recipes = mutableListOf<RecipeRequest>()
                for (recipe in 1..3) coroutineScope{
                    val mealResponse = nutritionService.createMeal(
                        CreateMealRequest(calories = userDetails.calories / 3, size = data.recipeAmount),
                        token
                    )
                    val meal: List<RecipeLiteResponse> = mealResponse.data ?: throw Exception(mealResponse.error)
                    val recipesId = meal.map { it.id }
                    recipes.add(RecipeRequest(recipesId))
                }
                val tmpDailyMenuRequest = DailyMenuRequest(day, recipes.toList())
                menus.add(tmpDailyMenuRequest)
            }
            val tmp = UserPlanRequest(data.description, data.planDays, menus.toList())
            val result: Response<PlanModelResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.post("$host/plan/user/${user.id}"){
                    this.setBodyJson(tmp)
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: NoUserDetails){
            Response(
                successfulOperation = false,
                code = 404,
                data = null,
                error = e.message!!
            )
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 401,
                data = null,
                error = e.toString()
            )
        } catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = null,
                error = t.toString()
            )
        }
    }

    override suspend fun deleteUserPlan(token: String): Response<out Any> = coroutineScope{
        try {
            checkToken(token)
            val user = userService.getUser(token)
            user.data ?: throw Exception("User not found")
            val result: Response<out Any> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.delete("$host/plan/user/${user.data.id}")
                httpConsumerService.checkResponse(response)
                Response(successfulOperation = true, code = response.status.value, data = null)
            }
            result
        }
        catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 401,
                data = null,
                error = e.toString()
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = null,
                error = t.toString()
            )
        }
    }
}