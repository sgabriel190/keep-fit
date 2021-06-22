package com.example.orchestrator_service.presentation.controllers.planning

import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
import com.example.orchestrator_service.business.models.plan.request.CreateUserPlanRequest
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/planning"])
class PlanController {
    @Autowired
    lateinit var planService: PlanServiceInterface

    @RequestMapping("/plan/user", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = planService.getUserPlan(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/plan/user", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun createUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String,
                       @RequestBody data: CreateUserPlanRequest
    ): ResponseEntity<Any> = runBlocking {
        val result = planService.createUserPlan(data, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/plan/user", method = [RequestMethod.DELETE])
    @ResponseBody
    @Async
    fun deleteUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = planService.deleteUserPlan(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}