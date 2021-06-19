package com.example.user_service.business.services

import com.example.user_service.business.interfaces.AuthServiceInterface
import com.example.user_service.business.security.jwt.JwtTokenProvider
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.AuthenticationResponse
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.business_models.RegisterResponse
import com.example.user_service.presentation.business_models.toUserEntity
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService: AuthServiceInterface {

    @Autowired
    private lateinit var userRepository: UserRepositoryInterface

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    override fun login(username: String, password: String): Response<AuthenticationResponse> {
        try {
            val userPasswordAuthToken = UsernamePasswordAuthenticationToken(username, password)
            val user = userRepository.getByUsername(username)
                ?: return Response(successfulOperation = false, data = null, code = 400, error = "Username $username not found")
            authenticationManager.authenticate(userPasswordAuthToken)
            val token = jwtTokenProvider.createToken(user.id, username, user.getRoles())
            val authResponse = AuthenticationResponse(user.id, username, token)
            return Response(
                successfulOperation = true,
                code = 200,
                data = authResponse
            )
        } catch (t: Throwable){
            return Response(successfulOperation = false, code = 400, data = null, error = t.toString(), message = t.message?: "")
        }
    }

    override fun register(data: RegisterRequest): Response<RegisterResponse> {
        return try {
            userRepository.insertData(data.toUserEntity())
            val result = userRepository.getByUsername(data.username) ?: throw Exception("User not found.")
            Response(
                successfulOperation = true,
                code = 201,
                data = RegisterResponse(
                    id = result.id,
                    username = result.userName
                )
            )
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString(), message = t.message?: "")
        }
    }

    override fun validateToken(token: String): Response<Boolean> {
        return try {
            val isValid = jwtTokenProvider.validateToken(token)
            if (isValid) {
                Response(successfulOperation = true, code = 200, data = true)
            } else {
                throw Exception("Token is expired.")
            }
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString(), message = t.message?: "")
        }
    }
}