package com.example.user_service.presentation.business_models

import com.example.user_service.persistence.entities.UserEntity
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)

fun RegisterRequest.toUserEntity() = UserEntity(
    userName = username,
    passWord = DatatypeConverter.printHexBinary(
        MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
    ).toLowerCase(),
    email = email
)
