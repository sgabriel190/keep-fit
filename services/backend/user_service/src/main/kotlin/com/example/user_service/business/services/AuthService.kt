package com.example.user_service.business.services

import com.example.user_service.business.interfaces.AuthServiceInterface
import com.example.user_service.persistence.entities.toUserModel
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.business_models.toUserModel
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService: AuthServiceInterface {

    @Autowired
    private lateinit var userRepository: UserRepositoryInterface

    override fun login(username: String, password: String): Response<UserModel> {
        return try {
            val user = userRepository.getByUsername(username = username) ?: throw Exception("No user found.")
            Response(successfulOperation = true, code = 200, data = user.toUserModel())
        } catch (t: Throwable){
            Response(successfulOperation = false, code = 400, data = null, error = t.toString()
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