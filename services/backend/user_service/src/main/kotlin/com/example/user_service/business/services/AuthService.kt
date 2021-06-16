package com.example.user_service.business.services

import com.example.user_service.business.interfaces.AuthServiceInterface
import com.example.user_service.business.security.jwt.JwtTokenProvider
import com.example.user_service.persistence.entities.toUserModel
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.AuthenticationResponse
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.business_models.toUserModel
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
            return Response(successfulOperation = false, code = 400, data = null, error = t.toString()
            )
        }
    }

    override fun register(data: RegisterRequest): Response<UserModel> {
        return try {
            userRepository.insertData(data.toUserModel())
            Response(successfulOperation = true, code = 204, data = null)
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString())
        }
    }
}