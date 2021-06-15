package com.example.user_service.business.services

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.persistence.entities.toUserModel
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.persistence.repositories.UserRepository
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService: UserServiceInterface {

    @Autowired
    private lateinit var userRepository: UserRepositoryInterface

    override fun deleteUser(id: Int): Response<UserModel> {
        try {
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

    override fun forgotPassword(data: ForgotPasswordRequest): Response<UserModel> {
        return try {
            userRepository.getByEmail(data.email) ?: throw Exception("User email does not exist.")
            val result = userRepository.getByUsername(data.username) ?: throw Exception("User username does not exist.")
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

}