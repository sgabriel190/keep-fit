package com.example.user_service.business.services

import com.example.user_service.persistence.entities.UserEntity
import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.persistence.data_values.models.UserModel
import com.example.user_service.persistence.interfaces.RepositoryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService: UserServiceInterface {

    @Autowired
    private lateinit var userRepository: RepositoryInterface<UserEntity, UserModel>

    override fun login(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(id: Int): String {
        TODO("Not yet implemented")
    }
}