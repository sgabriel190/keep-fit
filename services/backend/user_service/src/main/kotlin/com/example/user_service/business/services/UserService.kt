package com.example.user_service.business.services

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.business.security.jwt.JwtTokenProvider
import com.example.user_service.persistence.entities.toUserModel
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.business_models.ForgotPasswordResponse
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService: UserServiceInterface {

    @Autowired
    private lateinit var userRepository: UserRepositoryInterface

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    override fun deleteUser(token: String): Response<Any> {
        try {
            val claims = tokenProvider.getClaims(token)
            val id = claims["id"].toString().toInt()
            userRepository.deleteById(id)
            return Response(
                successfulOperation = true,
                code = 204,
                data = null
            )
        }
        catch (t: Throwable){
            return Response(
                successfulOperation = false,
                code = 400,
                error = t.toString(),
                data = null
            )
        }
    }

    override fun forgotPassword(data: ForgotPasswordRequest, token: String): Response<ForgotPasswordResponse> {
        return try {
            val claims = tokenProvider.getClaims(token)
            if (claims["username"] != data.username && claims["email"] != data.email){
                throw Exception("Can't do this operation on another user!")
            }
            userRepository.getByEmail(data.email) ?: throw Exception("User email does not exist.")
            val result = userRepository.getByUsername(data.username) ?: throw Exception("User username does not exist.")
            Response(
                successfulOperation = true,
                data = ForgotPasswordResponse(
                    id = result.id,
                    username = result.userName,
                    email = result.email
                ),
                code = 200
            )
        } catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                error = t.toString(),
                data = null
            )
        }
    }

    override fun getUser(token: String): Response<UserModel> {
        return try {
            val claims = tokenProvider.getClaims(token)
            val id = claims["id"].toString().toInt()
            val result = userRepository.getById(id) ?: throw Exception("User not found.")
            Response(successfulOperation = true, data = result.toUserModel(), code = 200)
        } catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                error = t.toString(),
                data = null
            )
        }
    }

    override fun updatePlanId(idUserDetails: Int, token: String): Response<UserModel> {
        return try {
            val claims = tokenProvider.getClaims(token)
            val id = claims["id"].toString().toInt()
            userRepository.updatePlanId(idUserDetails, id)
            val user = userRepository.getById(id) ?: throw Exception("User not found.")
            Response(successfulOperation = true, code = 200, data = user.toUserModel())
        } catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                error = t.toString(),
                data = null
            )
        }
    }

}