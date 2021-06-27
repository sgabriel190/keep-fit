package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.InvalidJwt
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.interfaces.*
import com.example.orchestrator_service.business.models.notification.EmailRequest
import com.example.orchestrator_service.business.models.nutrition.response.UserDetailResponse
import com.example.orchestrator_service.business.models.user.request.LoginRequest
import com.example.orchestrator_service.business.models.user.request.RegisterRequest
import com.example.orchestrator_service.business.models.user.response.AuthenticationResponse
import com.example.orchestrator_service.business.models.user.response.RegisterResponse
import com.example.orchestrator_service.business.models.user.response.UserModel
import com.example.orchestrator_service.business.models.user.response.UserProfileResponse
import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService: UserServiceInterface {
    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    @Autowired
    lateinit var notificationProducerService: NotificationProducerServiceInterface

    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @Autowired
    lateinit var planService: PlanServiceInterface

    private val host = Host(
        host = "http://${System.getenv("HOST_USER") ?: "localhost"}",
        port = "8080",
        path = "api/users"
    )

    private suspend fun checkToken(token: String) {
        val responseValidToken = this.validateToken(token)
        if (responseValidToken.code / 100 != 2){
            throw InvalidJwt("Invalid jwt.")
        }
    }

    override suspend fun authenticateUser(data: LoginRequest): Response<AuthenticationResponse> = coroutineScope{
        try {
            val result: Response<AuthenticationResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.post("$host/auth/login"){
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun registerUser(data: RegisterRequest): Response<RegisterResponse> = coroutineScope{
        try {
            val result: Response<RegisterResponse> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.put("$host/auth/register") {
                    this.setBodyJson(data)
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            if (result.code / 100 == 2) {
                val tmp = notificationProducerService.sendEmail(EmailRequest("Welcome", data.email, "Register"))
                if (tmp.code / 100 != 2) {
                    throw Exception(tmp.message + " " + tmp.error)
                }
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getUser(token: String): Response<UserModel> = coroutineScope{
        try {
            val result: Response<UserModel> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.get("$host/user"){
                    headers["Authorization"] = token
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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

    override suspend fun getUserProfile(token: String): Response<UserProfileResponse> = coroutineScope{
        try {
            val resultUser = getUser(token)
            if (!resultUser.successfulOperation) throw Exception(resultUser.error)
            resultUser.data ?: throw Exception("User info data is null")
            val resultUserDetails = nutritionService.getUserDetails(token)
            if (!resultUserDetails.successfulOperation) throw Exception(resultUserDetails.error)
            resultUserDetails.data ?: throw Exception("User details data is null")
            Response(
                successfulOperation = true,
                code = 200,
                data = UserProfileResponse(
                    id = resultUser.data.id,
                    username = resultUser.data.username,
                    email = resultUser.data.email,
                    userDetails = resultUserDetails.data
                )
            )
        } catch (e: InvalidJwt){
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

    override suspend fun validateToken(token: String): Response<Boolean> = coroutineScope {
        val result: Response<Boolean> = httpConsumerService.executeRequest {
            val response: HttpResponse = httpConsumerService.client.post("$host/auth/validate"){
                headers["Authorization"] = token
            }
            httpConsumerService.checkResponse(response)
            response.receive()
        }
        if (result.code / 100 != 2){
            throw Exception("Invalid JWT.")
        }
        result
    }

    override suspend fun deleteUser(token: String): Response<out Any> = coroutineScope {
        try {
            nutritionService.deleteUserDetails(token)
            planService.deleteUserPlan(token)
            val result = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.delete("$host/user"){
                    headers["Authorization"] = token
                }
                if (response.status.value != 204){
                    throw Exception("User was not deleted.")
                }
                Response(successfulOperation = true, code = 204, data = Any())
            }
            Response(successfulOperation = result.successfulOperation, code = result.code, data = null)
        } catch (e: InvalidJwt){
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

    override suspend fun updateUserDetails(token: String, userDetailsId: Int): Response<UserModel> = coroutineScope {
        try {
            checkToken(token)
            val result: Response<UserModel> = httpConsumerService.executeRequest {
                val response: HttpResponse = httpConsumerService.client.patch("$host/user/details/$userDetailsId"){
                    headers["Authorization"] = token
                }
                httpConsumerService.checkResponse(response)
                response.receive()
            }
            result
        } catch (e: InvalidJwt){
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