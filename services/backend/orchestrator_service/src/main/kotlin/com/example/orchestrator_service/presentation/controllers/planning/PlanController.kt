package com.example.orchestrator_service.presentation.controllers.planning

import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
<<<<<<< HEAD
import com.example.orchestrator_service.business.models.plan.request.CreateUserPlanRequest
=======
import com.example.orchestrator_service.business.models.plan.UserPlanRequest
>>>>>>> 5edfeaa93e45b6511bdfec2da5e30a5c4ef73913
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
<<<<<<< HEAD
    fun getUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = planService.getUserPlan(token)
=======
    fun getUserPlan(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String,
                    @PathVariable id: Int): ResponseEntity<Any> = runBlocking {
        val result = planService.getUserPlan(id, token)
>>>>>>> 5edfeaa93e45b6511bdfec2da5e30a5c4ef73913
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