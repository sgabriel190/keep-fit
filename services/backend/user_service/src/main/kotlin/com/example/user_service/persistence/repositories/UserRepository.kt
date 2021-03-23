package com.example.user_service.persistence.repositories

import com.example.user_service.persistence.entities.UserEntity
import com.example.user_service.persistence.data_values.models.UserModel
import com.example.user_service.persistence.interfaces.RepositoryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRepository: RepositoryInterface<UserEntity, UserModel> {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    override fun getById(id: Int): UserEntity {
        TODO("Not yet implemented")
    }

    override fun updateById(id: Int): UserEntity {
        TODO("Not yet implemented")
    }

    override fun insertData(data: UserModel): UserEntity {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): UserEntity {
        TODO("Not yet implemented")
    }
}