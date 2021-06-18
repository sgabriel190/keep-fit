package com.example.orchestrator_service.presentation

import com.example.orchestrator_service.business.interfaces.OrchestratorServiceInterface
import com.example.orchestrator_service.business.models.LoginRequest
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend"])
class OrchestratorController {
    @Autowired
    lateinit var orchestratorService: OrchestratorServiceInterface

    @RequestMapping("/ping", method = [RequestMethod.GET])
    @ResponseBody
    fun ping(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/login", method = [RequestMethod.POST])
    @ResponseBody
    fun authenticateUser(@RequestBody data: LoginRequest): ResponseEntity<Any> = runBlocking {
        val result = orchestratorService.authenticateUser(data)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/register", method = [RequestMethod.POST])
    @ResponseBody
    fun registerUser(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/plan", method = [RequestMethod.POST])
    @ResponseBody
    fun createUserPlan(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/recipe", method = [RequestMethod.GET])
    @ResponseBody
    fun getRecipes(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/profile", method = [RequestMethod.GET])
    @ResponseBody
    fun getUserProfile(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/profile", method = [RequestMethod.PUT])
    @ResponseBody
    fun updateUserProfile(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }

    @RequestMapping("/user/plan", method = [RequestMethod.GET])
    @ResponseBody
    fun getUserPlan(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(null)
    }
}