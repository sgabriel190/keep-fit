package com.example.user_service.presentation.controllers

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.persistence.data_values.requests.LoginRequest
import com.example.user_service.persistence.data_values.requests.LogoutRequest
import com.example.user_service.persistence.data_values.requests.RegisterRequest
import com.example.user_service.persistence.data_values.responses.LoginResponse
import com.example.user_service.persistence.data_values.responses.LogoutResponse
import com.example.user_service.persistence.data_values.responses.RegisterResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserServiceInterface

    @RequestMapping("/login", method=[RequestMethod.POST])
    @ResponseBody
    fun login(data: LoginRequest): ResponseEntity<LoginResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse(idUser = 10))
    }

    @RequestMapping("/register", method=[RequestMethod.POST])
    @ResponseBody
    fun register(data: RegisterRequest): ResponseEntity<RegisterResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(RegisterResponse(idUser = 10))
    }

    @RequestMapping("/logout", method=[RequestMethod.POST])
    @ResponseBody
    fun logout(data: LogoutRequest): ResponseEntity<LogoutResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(LogoutResponse(idUser = 10))
    }

    @RequestMapping("/delete_user", method=[RequestMethod.DELETE])
    @ResponseBody
    fun deleteUser(): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body("Delete user")
    }
}