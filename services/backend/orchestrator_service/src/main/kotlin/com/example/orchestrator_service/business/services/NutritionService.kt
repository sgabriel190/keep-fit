package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.config.setQueryParams
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.nutrition.CreateMeal
import com.example.orchestrator_service.business.models.nutrition.CreateUserDetails
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.request.*
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
            throw Exception(responseValidToken.error)
        }
    }

    override suspend fun getImage(imagePath: String, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: ByteArray = httpConsumerService.client.get("$host/image/$imagePath")
                response
            }
            Response(successfulOperation = true, code = 200, data = result)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getRecipes(params: Map<String, Any>, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/recipe"){
                    this.setQueryParams(params)
                }
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getRecipe(id: Int, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/recipe/$id")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getGenders(token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/userDetails/gender")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getCategories(token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/recipe/category")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun createMeal(data: CreateMeal, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/menu"){
                    this.setBodyJson(data)
                }
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getCategory(id: Int, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/recipe/category/$id")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getDietTypes(token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/userDetails/dietType")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getActivityTypes(token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/userDetails/activityType")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getActivityType(id: Int, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/userDetails/activityType/$id")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getUserDetails(id: Int, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.get("$host/userDetails/$id")
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun addUserDetails(data: CreateUserDetails, token: String): Response<Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: Response<Any> = httpConsumerService.client.post("$host/userDetails"){
                    this.setBodyJson(data)
                }
                if (response.code / 100 != 2){
                    throw Exception(response.message + " " + response.error)
                }
                response
            }
            result
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }
}