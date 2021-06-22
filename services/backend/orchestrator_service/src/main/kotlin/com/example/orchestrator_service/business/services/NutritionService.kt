package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.InvalidJwt
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.config.setQueryParams
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.nutrition.request.CreateMealRequest
import com.example.orchestrator_service.business.models.nutrition.request.UserDetailsRequest
import com.example.orchestrator_service.business.models.nutrition.response.*
import com.example.orchestrator_service.business.models.user.response.UserModel
import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NutritionService: NutritionServiceInterface {

    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    @Autowired
    lateinit var userService: UserServiceInterface

    private val host = Host("http://localhost:2020/api/nutrition")

    private suspend fun checkToken(token: String) {
        val responseValidToken = userService.validateToken(token)
        if (responseValidToken.code / 100 != 2){
            throw InvalidJwt("Invalid jwt.")
        }
    }

    override suspend fun getImage(imagePath: String, token: String): Response<out Any> = coroutineScope{
        try {
            checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: ByteArray = httpConsumerService.client.get("$host/image/$imagePath")
                response
            }
            Response(successfulOperation = true, code = 200, data = result)
        } catch (e: InvalidJwt){
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

    override suspend fun getRecipes(params: Map<String, Any>, token: String): Response<List<RecipeLiteResponse>> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<List<RecipeLiteResponse>> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe"){
                    this.setQueryParams(params)
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getRecipe(id: Int, token: String): Response<RecipeResponse> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<RecipeResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe/$id")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getGenders(token: String): Response<List<GenderResponse>> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<List<GenderResponse>> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/gender")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getCategories(token: String): Response<List<CategoryResponse>> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<List<CategoryResponse>> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe/category")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun createMeal(data: CreateMealRequest, token: String): Response<List<RecipeLiteResponse>> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<List<RecipeLiteResponse>> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/meal"){
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getCategory(id: Int, token: String): Response<CategoryResponse> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<CategoryResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe/category/$id")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getDietTypes(token: String): Response<List<DietTypeResponse>> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<List<DietTypeResponse>> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/dietType")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getActivityTypes(token: String): Response<List<ActivityTypeResponse>> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<List<ActivityTypeResponse>> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/activityType")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getActivityType(id: Int, token: String): Response<ActivityTypeResponse> = coroutineScope{
        try {
            checkToken(token)
            val result: Response<ActivityTypeResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/activityType/$id")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getUserDetails(token: String): Response<UserDetailResponse> = coroutineScope{
        try {
            checkToken(token)
            val userResponse = userService.getUser(token)
            val user: UserModel = userResponse.data ?: throw Exception(userResponse.error)
            val result: Response<UserDetailResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/${user.idUserDetails}")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun deleteUserDetails(token: String): Response<out Any> = coroutineScope{
        try {
            checkToken(token)
            val userResponse = userService.getUser(token)
            val user: UserModel = userResponse.data ?: throw Exception(userResponse.error)
            val result: Response<Any> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.delete("$host/userDetails/${user.idUserDetails}")
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            val updateUserResult = userService.updateUserDetails(token, -1)
            updateUserResult.data ?: throw Exception(updateUserResult.error)
            Response(successfulOperation = result.successfulOperation, code = result.code, data = result.data)
        } catch (e: InvalidJwt){
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

    override suspend fun addUserDetails(data: UserDetailsRequest, token: String): Response<Int> = coroutineScope {
        try {
            checkToken(token)
            val result: Response<Int> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.post("$host/userDetails"){
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            val updateUserResult = userService.updateUserDetails(token, result.data!!)
            updateUserResult.data ?: throw Exception(updateUserResult.error)
            Response(successfulOperation = result.successfulOperation, code = result.code, data = result.data)
        } catch (e: InvalidJwt){
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