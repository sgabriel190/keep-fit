package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.config.setQueryParams
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
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

    private val host = Host("http://localhost:2020/api/nutrition")

    override suspend fun getImage(imagePath: String): Response<Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: ByteArray = httpConsumerService.client.get("$host/image/$imagePath")
                response
            }
            Response(successfulOperation = true, code = 200, data = result)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }

    override suspend fun getRecipes(params: Map<String, Any>): Response<Any> {
        return try {
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

    override suspend fun getRecipe(id: Int): Response<Any> {
        return try {
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

    override suspend fun getGenders(): Response<Any> {
        return try {
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

    override suspend fun getCategories(): Response<Any> {
        return try {
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

    override suspend fun createMeal(data: CreateMeal): Response<Any> {
        return try {
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

    override suspend fun getCategory(id: Int): Response<Any> {
        return try {
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

    override suspend fun getDietTypes(): Response<Any> {
        return try {
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

    override suspend fun getActivityTypes(): Response<Any> {
        return try {
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

    override suspend fun getActivityType(id: Int): Response<Any> {
        return try {
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

    override suspend fun getUserDetails(id: Int): Response<Any> {
        return try {
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

    override suspend fun addUserDetails(data: CreateUserDetails): Response<Any> {
        return try {
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