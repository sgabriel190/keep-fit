package com.example.user_service.business.services

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.persistence.entities.toUserModel
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.persistence.repositories.UserRepository
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService: UserServiceInterface {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun login(username: String, password: String): Response<UserModel> {
        return try {
            val user = userRepository.getByUsername(username = username)
            Response(
                successfulOperation = true,
                code = 200,
                data = user!!.toUserModel()
            )
        } catch (t: Throwable){
            Response(
                successfulOperation = false,
                code = 400,
                data = null
            )
        }
    }

    override fun register(data: RegisterRequest): Response<UserModel> {
        val tmp = UserModel(
            username = data.username,
            password = data.password,
            email = data.email
        )
        val result = userRepository.insertData(tmp)
        return if (result){
            Response(
                successfulOperation = true,
                code = 204,
                data = null
            )
        } else {
            Response(
                successfulOperation = false,
                code = 400,
                data = null
            )
        }
    }

    override fun deleteUser(id: Int): Response<UserModel> {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(id: Int): Response<UserModel> {
        TODO("Not yet implemented")
    }

}