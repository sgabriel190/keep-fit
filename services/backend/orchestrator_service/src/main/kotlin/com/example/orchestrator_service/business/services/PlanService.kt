package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.InvalidJwt
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.plan.UserPlanRequest
import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanService: PlanServiceInterface {

    private val host = Host("http://localhost:2021/api/planning")

    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    @Autowired
    lateinit var userService: UserServiceInterface

    private suspend fun checkToken(token: String) {
        val responseValidToken = userService.validateToken(token)
        if (responseValidToken.code / 100 != 2){
            throw InvalidJwt("Invalid jwt.")
        }
    }

    override suspend fun getUserPlan(idUser: Int, token: String): Response<out Any> {
        return try {
            this.checkToken(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/plan/user/{id}")
                httpConsumerService.checkResponse(response)
            }
            result
        }
        catch (e: InvalidJwt){
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

    override suspend fun getMeals(): Response<out Any> {
        TODO("Not yet implemented")
    }

    override suspend fun getMealRecipes(): Response<out Any> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlans(): Response<out Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/plan")
                httpConsumerService.checkResponse(response)
            }
            result
        }
        catch (e: InvalidJwt){
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

    override suspend fun getMenus(): Response<out Any> {
        TODO("Not yet implemented")
    }

    override suspend fun createPlan(idUser: Int, data: UserPlanRequest): Response<out Any> {
        return try {
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.post("$host/plan/user/$idUser"){
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
            }
            result
        }
        catch (e: InvalidJwt){
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