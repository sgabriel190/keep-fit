package com.example.user_service.business.services

import com.example.user_service.persistence.entities.UserEntity
import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.persistence.interfaces.RepositoryInterface
import com.example.user_service.persistence.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService: UserServiceInterface {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun login(username: String, password: String): Boolean {
        return try {
            val user = userRepository.getByUsername(username = username)
            user!!.password == password
        } catch (t: Throwable){
            false
        }
    }

    override fun register(data: UserModel): Boolean {
        return userRepository.insertData(data)
    }

    override fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(id: Int): String {
        TODO("Not yet implemented")
    }

    override fun test(id: Int): UserModel {
        val result = userRepository.getById(id)
        return UserModel(
            username = result.username,
            password = result.password,
            email = result.email,
            idUserDetails = result.idUserDetails,
            idDietPlan = result.idDietPlan
        )
    }
}