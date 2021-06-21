package com.example.orchestrator_service.presentation.controllers.user

import com.example.orchestrator_service.business.interfaces.UserServiceInterface
import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/user"])
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

    @RequestMapping("/user/plan", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun createUserPlan(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/profile", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getUserProfile(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/profile", method = [RequestMethod.PUT])
    @ResponseBody
    @Async
    suspend fun updateUserProfile(): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/plan", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    suspend fun getUserPlan(): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(null)
    }
}