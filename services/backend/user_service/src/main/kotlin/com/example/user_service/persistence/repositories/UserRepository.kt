package com.example.user_service.persistence.repositories

import com.example.user_service.persistence.entities.UserEntity
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.persistence.interfaces.RepositoryInterface
import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import com.example.user_service.persistence.row_mappers.UserRowMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository

@Repository
class UserRepository: RepositoryInterface<UserEntity, UserModel>, UserRepositoryInterface {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    override fun getById(id: Int): UserEntity {
        val sqlQuery = "SELECT * " +
                "FROM users " +
                "WHERE ID = $id"
        val result = this.jdbcTemplate.queryForObject(sqlQuery, UserRowMapper())
        return result!!
    }

    override fun updateById(id: Int): UserEntity {
        val sqlQuery = "UPDATE users " +
                "SET username = ?, password = ?, email = ?, ID_user_details, ID_diet_plan = ? " +
                "WHERE ID = $id"
        this.jdbcTemplate.update(sqlQuery, "test", "test", "test", 1, 1)
        return UserEntity(
            id = 1,
            username = "test",
            password = "test",
            email = "test",
            idUserDetails = 1,
            idDietPlan = 1
        )
    }

    override fun insertData(data: UserModel): Boolean {
        return try{
            val sqlQuery = "INSERT INTO users(username, password, email) VALUES (?, ?, ?)"
            jdbcTemplate.update(sqlQuery, data.username, data.password, data.email)
            true
        } catch (e: Throwable){
            println("Error Insert!")
            false
        }
    }

    override fun deleteById(id: Int): Boolean {
        return try{
            val sqlQueryDelete = "DELETE FROM users WHERE ID = ?"
            jdbcTemplate.update(sqlQueryDelete, id)
            true
        }
        catch (e: Throwable){
            println("Error Delete!")
            false
        }
    }

    override fun getByUsername(username: String): UserEntity? {
        val sqlQueryGetByName = "SELECT * FROM users WHERE name = :name"
        return jdbcTemplate.queryForObject(sqlQueryGetByName,
            UserRowMapper(),
            MapSqlParameterSource().addValue("name", username))
    }

    override fun getByEmail(email: String): UserEntity? {
        val sqlQueryGetByEmail = "SELECT * FROM users WHERE email = :email"
        return jdbcTemplate.queryForObject(sqlQueryGetByEmail,
            UserRowMapper(),
            MapSqlParameterSource().addValue("email", email))
    }
}