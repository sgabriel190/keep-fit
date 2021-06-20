package com.example.user_service.presentation.controllers

import com.example.user_service.business.interfaces.UserServiceInterface
import com.example.user_service.presentation.business_models.ForgotPasswordRequest
import com.example.user_service.presentation.http.MyError
import com.example.user_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserServiceInterface

    @Async
    @RequestMapping("/user", method=[RequestMethod.DELETE])
    @ResponseBody
    fun deleteUser(@RequestHeader(name="Authorization") token: String): ResponseEntity<Any>{
        val result = userService.deleteUser(token.split(" ")[1])
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

    @Async
    @RequestMapping("/user", method=[RequestMethod.GET])
    @ResponseBody
    fun getUser(@RequestHeader(name="Authorization") token: String): ResponseEntity<Any>{
        val result = userService.getUser(token.split(" ")[1])
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

    @Async
    @RequestMapping("/forgotPassword", method=[RequestMethod.POST])
    @ResponseBody
    fun forgotPassword(@RequestBody data: ForgotPasswordRequest,
                       @RequestHeader(name="Authorization") token: String): ResponseEntity<Any>{
        val result = userService.forgotPassword(data, token.split(" ")[1])
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

    @Async
    @RequestMapping("/user/details/{idUserDetails}", method=[RequestMethod.PATCH])
    @ResponseBody
    fun updatePlanId(
        @PathVariable idUserDetails: Int,
        @RequestHeader(name="Authorization") token: String): ResponseEntity<Any>{
        val result = userService.updatePlanId(idUserDetails, token.split(" ")[1])
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