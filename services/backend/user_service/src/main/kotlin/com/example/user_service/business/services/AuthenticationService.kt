package com.example.user_service.business.services

import com.example.user_service.business.interfaces.AuthenticationServiceInterface
import com.example.user_service.business.security.jwt.JwtTokenProvider
import com.example.user_service.persistence.repositories.UserRepository
import com.example.user_service.presentation.responses.HTTPResponse
import com.example.user_service.presentation.responses.LoginResponse
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

    override fun signIn(username: String?, password: String?): HTTPResponse {
        try{
            if (username == null || password == null){
                return HTTPResponse(success = false, code = 400, data = null, message = "Missing username/password")
            }
            val userPasswordAuthToken = UsernamePasswordAuthenticationToken(username, password)
            authenticationManager.authenticate(userPasswordAuthToken)
            val foundUser = usersRepository.getByUsername(username) ?: return HTTPResponse(success = false, data = null, code = 400)
            val token = jwtTokenProvider.createToken(foundUser.id, username)
            return HTTPResponse(success = true, code = 200, data = LoginResponse(idUser = foundUser.id, user = username, jwtToken = token)
            )
        } catch (exception: AuthenticationException){
            return HTTPResponse(success = false, data = null, code = 400, message = exception.message ?: "")
        }
    }
}