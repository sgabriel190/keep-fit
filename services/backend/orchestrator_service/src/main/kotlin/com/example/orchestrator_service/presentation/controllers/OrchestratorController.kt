package com.example.orchestrator_service.presentation.controllers

import com.example.orchestrator_service.business.interfaces.OrchestratorServiceInterface
import com.example.orchestrator_service.business.models.user.LoginRequest
import com.example.orchestrator_service.business.models.user.RegisterRequest
import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend"])
class OrchestratorController {
    @Autowired
    lateinit var orchestratorService: OrchestratorServiceInterface

    @RequestMapping("/ping", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun ping(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(Response(successfulOperation = true, data = null, code = 200))
    }

    @RequestMapping("/login", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun authenticateUser(@RequestBody data: LoginRequest): ResponseEntity<Any> = runBlocking {
        val result = orchestratorService.authenticateUser(data)
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
        val result = orchestratorService.registerUser(data)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/image/{imgDir}/{imgName}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getImage(@PathVariable imgDir: String,
                 @PathVariable imgName: String): ResponseEntity<Any> = runBlocking {
        val result = orchestratorService.getImage("$imgDir/$imgName")
        if (result.successfulOperation){
            ResponseEntity.status(200)
                .contentType(MediaType.IMAGE_JPEG)
                .body(result.data)
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

    @RequestMapping("/recipe", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getRecipes(): ResponseEntity<Any> = runBlocking {
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