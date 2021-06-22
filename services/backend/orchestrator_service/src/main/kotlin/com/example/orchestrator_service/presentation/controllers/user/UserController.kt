package com.example.orchestrator_service.presentation.controllers.user

import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.user.request.LoginRequest
import com.example.orchestrator_service.business.models.user.request.RegisterRequest
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/users"])
class UserController {
    @Autowired
    lateinit var userService: UserServiceInterface

    @RequestMapping("/login", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun authenticateUser(@RequestBody data: LoginRequest): ResponseEntity<Any> = runBlocking {
        val result = userService.authenticateUser(data)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/register", method = [RequestMethod.PUT])
    @ResponseBody
    @Async
    fun registerUser(@RequestBody data: RegisterRequest): ResponseEntity<Any> = runBlocking {
        val result = userService.registerUser(data)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/user", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getUser(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = userService.getUser(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/user", method = [RequestMethod.DELETE])
    @ResponseBody
    @Async
    fun deleteUser(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = userService.deleteUser(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/user/details/{id}", method = [RequestMethod.PATCH])
    @ResponseBody
    @Async
    fun updateUserProfile(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String,
                                  @PathVariable id: Int): ResponseEntity<Any> = runBlocking {
        val result = userService.updateUserDetails(token, id)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}