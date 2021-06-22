package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.InvalidJwt
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.nutrition.request.CreateMealRequest
import com.example.orchestrator_service.business.models.nutrition.response.RecipeLiteResponse
import com.example.orchestrator_service.business.models.nutrition.response.UserDetailResponse
import com.example.orchestrator_service.business.models.plan.request.CreateUserPlanRequest
import com.example.orchestrator_service.business.models.plan.request.DailyMenuRequest
import com.example.orchestrator_service.business.models.plan.request.RecipeRequest
import com.example.orchestrator_service.business.models.plan.request.UserPlanRequest
import com.example.orchestrator_service.business.models.plan.response.PlanModelResponse
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

    private val host = Host("http://localhost:2021/api/planning")

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

    override suspend fun getUserPlan(token: String): Response<PlanModelResponse> = coroutineScope{
        try {
            checkToken(token)
            val user = userService.getUser(token)
            val result: Response<PlanModelResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/plan/user/${user.data!!.id}")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        }
        catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
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
            if (user.idUserDetails != null){
                // Delete existing plan
            }
            val userDetailsResponse = nutritionService.getUserDetails(token)
            val userDetails: UserDetailResponse = userDetailsResponse.data ?: throw Exception(userDetailsResponse.error)
            val menus = mutableListOf<DailyMenuRequest>()
            for (day in 1..data.planDays) coroutineScope {
                val recipes = mutableListOf<RecipeRequest>()
                for (recipe in 1..data.recipeAmount) coroutineScope{
                    val mealResponse = nutritionService.createMeal(
                        CreateMealRequest(userDetails.calories, data.recipeAmount),
                        token
                    )
                    val meal: List<RecipeLiteResponse> = mealResponse.data ?: throw Exception(mealResponse.error)
                    recipes.add(RecipeRequest(meal.map { it.id }))
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
        }
        catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
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

    override suspend fun deleteUserPlan(token: String): Response<out Any> = coroutineScope{
        try {
            checkToken(token)
            val user = userService.getUser(token)
            val result: Response<out Any> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.delete("$host/plan/user/${user.data!!.id}")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        }
        catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
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