package com.example.user_service.persistence.row_mappers

import com.example.user_service.persistence.entities.UserEntity
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException
import kotlin.jvm.Throws

class UserRowMapper: RowMapper<UserEntity?> {
    @Throws(SQLException::class)
    override fun mapRow(resultSet: ResultSet, rowNum: Int):UserEntity{
        return UserEntity(
            id = resultSet.getString("ID")!!.toInt(),
            userName = resultSet.getString("username").toString(),
            passWord = resultSet.getString("password").toString(),
            email = resultSet.getString("email"),
            idUserDetails = resultSet.getString("ID_user_details")?.toInt(),
            targetCalories = resultSet.getString("target_calories")!!.toInt()
        )
    }
}