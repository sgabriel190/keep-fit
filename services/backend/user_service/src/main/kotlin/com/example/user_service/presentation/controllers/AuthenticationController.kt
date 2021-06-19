package com.example.user_service.presentation.controllers

import com.example.user_service.business.interfaces.AuthServiceInterface
import com.example.user_service.presentation.business_models.LoginRequest
import com.example.user_service.presentation.business_models.RegisterRequest
import com.example.user_service.presentation.http.MyError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/auth")
class AuthenticationController {

    @Autowired
    lateinit var authService: AuthServiceInterface

    @Async
    @RequestMapping("/register", method=[RequestMethod.PUT])
    @ResponseBody
    fun register(@RequestBody data: RegisterRequest): ResponseEntity<Any> {
        val result = authService.register(data)
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

    @Async
    @RequestMapping("/login", method=[RequestMethod.POST])
    @ResponseBody
    fun login(@RequestBody data: LoginRequest): ResponseEntity<Any>{
        val result = authService.login(data.username, data.password)
        return if (result.successfulOperation) {
            ResponseEntity
                .status(result.code)
                .body(result)
        } else {
            ResponseEntity
                .status(result.code)
                .body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @Async
    @RequestMapping("/validate", method=[RequestMethod.POST])
    @ResponseBody
    fun validate(@RequestHeader(name="Authorization") token: String): ResponseEntity<Any>{
        val result = authService.validateToken(token.split(" ")[1])
        return if (result.successfulOperation) {
            ResponseEntity
                .status(result.code)
                .body(result)
        } else {
            ResponseEntity
                .status(result.code)
                .body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}