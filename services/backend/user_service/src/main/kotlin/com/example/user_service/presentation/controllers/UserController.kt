package com.example.user_service.presentation.controllers

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.business_models.LogoutRequest
import com.example.user_service.presentation.http.MyError
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserServiceInterface

    @RequestMapping("/ping", method=[RequestMethod.GET])
    @ResponseBody
    fun ping(): ResponseEntity<Any>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response(successfulOperation = true, code = 200, data = null))
    }

    @RequestMapping("/user/{id}", method=[RequestMethod.DELETE])
    @ResponseBody
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Any>{
        val result = userService.deleteUser(id)
        return if (result.successfulOperation){
            ResponseEntity
                .status(result.code)
                .body(result)
        } else {
            ResponseEntity
                .status(result.code)
                .body(MyError(
                    code = result.code,
                    error = result.error,
                    info = result.message
                ))
        }
    }

    @RequestMapping("/forgotPassword", method=[RequestMethod.POST])
    @ResponseBody
    fun forgotPassword(@RequestBody data: ForgotPasswordRequest): ResponseEntity<Any>{
        val result = userService.forgotPassword(data)
        return if (result.successfulOperation){
            ResponseEntity
                .status(result.code)
                .body(result)
        } else {
            ResponseEntity
                .status(result.code)
                .body(MyError(
                    code = result.code,
                    error = result.error,
                    info = result.message
                ))
        }
    }
}