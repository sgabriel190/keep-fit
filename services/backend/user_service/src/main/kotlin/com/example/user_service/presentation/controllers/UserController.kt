package com.example.user_service.presentation.controllers

import com.example.user_service.business.interfaces.AuthenticationServiceInterface
import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.presentation.http.MyError
import com.example.user_service.presentation.http.Response
import com.example.user_service.presentation.business_models.LoginRequest
import com.example.user_service.presentation.business_models.LogoutRequest
import com.example.user_service.presentation.business_models.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserServiceInterface

    @Autowired
    private lateinit var authenticationServiceInterface: AuthenticationServiceInterface

    @RequestMapping("/logIn", method=[RequestMethod.POST])
    @ResponseBody
    fun login(@RequestBody data: LoginRequest): ResponseEntity<Any>{
        val response = authenticationServiceInterface.signIn(data.username, data.password)
        return ResponseEntity.status(response.code).body(response)
    }

    @RequestMapping("/ping", method=[RequestMethod.GET])
    @ResponseBody
    fun ping(): ResponseEntity<Any>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response(successfulOperation = true, code = 200, data = null))
    }

    @RequestMapping("/register", method=[RequestMethod.PUT])
    @ResponseBody
    fun register(@RequestBody data: RegisterRequest): ResponseEntity<Any>{
        val result = userService.register(data)
        return if ( result.successfulOperation ){
            ResponseEntity
                .status(result.code)
                .body(result)
        } else {
            ResponseEntity
                .status(result.code)
                .body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/logout", method=[RequestMethod.POST])
    @ResponseBody
    fun logout(@RequestBody data: LogoutRequest): ResponseEntity<Any>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response(successfulOperation = true, code = 204, data = null))
    }

    @RequestMapping("/user/{id}", method=[RequestMethod.DELETE])
    @ResponseBody
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Any>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response(successfulOperation = true, code = 204, data = null))
    }
}