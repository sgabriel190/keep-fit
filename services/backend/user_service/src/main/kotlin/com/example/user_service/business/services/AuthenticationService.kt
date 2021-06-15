package com.example.user_service.business.services

import com.example.user_service.business.interfaces.AuthenticationServiceInterface
import com.example.user_service.business.security.jwt.JwtTokenProvider
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.persistence.repositories.UserRepository
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service

@Service
class AuthenticationService: AuthenticationServiceInterface {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var usersRepository: UserRepository

    override fun signIn(username: String?, password: String?): Response<UserModel> {
        try{
            if (username == null || password == null){
                return Response(successfulOperation  = false, code = 400, data = null, message = "Missing username/password")
            }
            val userPasswordAuthToken = UsernamePasswordAuthenticationToken(username, password)
            authenticationManager.authenticate(userPasswordAuthToken)
            val foundUser = usersRepository.getByUsername(username) ?: return Response(successfulOperation = false, data = null, code = 400)
            val token = jwtTokenProvider.createToken(foundUser.id, username)
            return Response(successfulOperation = true, code = 200, data = null)
        } catch (exception: AuthenticationException){
            return Response(successfulOperation = false, data = null, code = 400, message = exception.message ?: "")
        }
    }
}