package com.example.orchestrator_service.presentation

import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/backend"])
class OrchestratorController {
    @Autowired
    lateinit var httpConsumerService: HttpConsumerServiceInterface

    @RequestMapping("/test", method = [RequestMethod.GET])
    @ResponseBody
    fun test(): ResponseEntity<Any> = runBlocking {
        val result = httpConsumerService.get()
        ResponseEntity.status(200).body(result)
    }

}