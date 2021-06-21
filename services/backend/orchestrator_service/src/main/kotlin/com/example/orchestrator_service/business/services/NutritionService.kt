package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.InvalidJwt
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.config.setQueryParams
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.nutrition.CreateMeal
import com.example.orchestrator_service.business.models.nutrition.CreateUserDetails
import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.request.*
import io.ktor.client.statement.*
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

    override suspend fun getImage(imagePath: String, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: ByteArray = httpConsumerService.client.get("$host/image/$imagePath")
                response
            }
            Response(successfulOperation = true, code = 200, data = result)
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getRecipes(params: Map<String, Any>, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe"){
                    this.setQueryParams(params)
                }
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getRecipe(id: Int, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe/$id")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getGenders(token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/gender")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getCategories(token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe/category")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun createMeal(data: CreateMeal, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/menu"){
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getCategory(id: Int, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/recipe/category/$id")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getDietTypes(token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/dietType")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getActivityTypes(token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/activityType")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getActivityType(id: Int, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/activityType/$id")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun getUserDetails(id: Int, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/userDetails/$id")
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }

    override suspend fun addUserDetails(data: CreateUserDetails, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.post("$host/userDetails"){
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
            }
            result
        } catch (e: InvalidJwt){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = e.toString(),
                    info = "",
                    code = 400
                )
            )
        }
        catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = MyError(
                    error = t.toString(),
                    info = "",
                    code = 400
                )
            )
        }
    }
}