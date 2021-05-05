package com.example.user_service.presentation.controllers

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.persistence.models.UserModel
import com.example.user_service.presentation.requests.LoginRequest
import com.example.user_service.presentation.requests.LogoutRequest
import com.example.user_service.presentation.requests.RegisterRequest
import com.example.user_service.presentation.responses.HTTPResponse
import com.example.user_service.presentation.responses.LoginResponse
import com.example.user_service.presentation.responses.LogoutResponse
import com.example.user_service.presentation.responses.RegisterResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @RequestMapping("/test/{id}", method=[RequestMethod.GET])
    @ResponseBody
    fun test(@PathVariable id: Int): ResponseEntity<HTTPResponse>{
        val result = userService.test(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(HTTPResponse(true, HttpStatus.OK.value()))
    }

    @RequestMapping("/register", method=[RequestMethod.PUT])
    @ResponseBody
    fun register(data: RegisterRequest): ResponseEntity<HTTPResponse>{
        val userData = UserModel(
            username = data.username,
            password = data.password,
            email = data.email
        )
        val result = userService.register(userData)
        val responseCode = if(result) HttpStatus.CREATED else HttpStatus.BAD_REQUEST
        return ResponseEntity
            .status(responseCode)
            .body(HTTPResponse(result, responseCode.value()))
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