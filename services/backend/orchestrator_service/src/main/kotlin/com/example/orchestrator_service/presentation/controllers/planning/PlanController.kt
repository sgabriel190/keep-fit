package com.example.orchestrator_service.presentation.controllers.planning

import com.example.orchestrator_service.business.interfaces.PlanServiceInterface
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
}