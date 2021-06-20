package com.example.user_service.persistence.repositories

import com.example.user_service.persistence.entities.UserEntity
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.persistence.row_mappers.UserRowMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRepository: UserRepositoryInterface {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    override fun getById(id: Int): UserEntity? {
        val sqlQuery = "SELECT * " +
                "FROM users " +
                "WHERE ID = $id"
        return jdbcTemplate.queryForObject(sqlQuery, UserRowMapper())
    }

    override fun updateById(id: Int, data: UserEntity): UserEntity? {
        val sqlQuery = "UPDATE users " +
                "SET username = ?, password = ?, email = ?, ID_user_details = ? " +
                "WHERE ID = $id"
        this.jdbcTemplate.update(sqlQuery, data.username, data.password, data.email, data.idUserDetails)
        return this.getById(id)
    }

    override fun insertData(data: UserEntity) {
        val sqlQuery = "INSERT INTO users(username, password, email) VALUES (?, ?, ?, ?)"
        jdbcTemplate.update(sqlQuery, data.username, data.password, data.email)
    }

    override fun deleteById(id: Int) {
        val sqlQueryDelete = "DELETE FROM users WHERE ID = ?"
        jdbcTemplate.update(sqlQueryDelete, id)
    }

    override fun getByUsername(username: String): UserEntity? {
        val sqlQueryGetByName = "SELECT * FROM users WHERE username = ?"
        return jdbcTemplate.queryForObject(
            sqlQueryGetByName,
            UserRowMapper(),
            username
        )
    }

    override fun getByEmail(email: String): UserEntity? {
        val sqlQueryGetByEmail = "SELECT * FROM users WHERE email = ?"
        return jdbcTemplate.queryForObject(
            sqlQueryGetByEmail,
            UserRowMapper(),
            email
        )
    }

    override fun updatePlanId(idUserDetails: Int, id: Int) {
        val sqlQueryUpdatePlanId = "UPDATE users SET ID_user_details = ? WHERE id = ?"
        jdbcTemplate.update(sqlQueryUpdatePlanId, idUserDetails, id)
    }
}