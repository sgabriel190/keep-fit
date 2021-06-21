package com.example.orchestrator_service.presentation.controllers.planning

import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
import com.example.orchestrator_service.business.models.plan.UserPlanRequest
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

    @RequestMapping("/plan/user/{id}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String,
                    @PathVariable id: Int): ResponseEntity<Any> = runBlocking {
        val result = planService.getUserPlan(id, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(result.data)
        }
    }

    @RequestMapping("/plan", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getPlans(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = planService.getPlans()
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(result.data)
        }
    }

    @RequestMapping("/plan/user/{idUser}", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun createUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String,
                       @PathVariable idUser: Int,
                       @RequestBody data: UserPlanRequest): ResponseEntity<Any> = runBlocking {
        val result = planService.createPlan(idUser, data)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(result.data)
        }
    }
}