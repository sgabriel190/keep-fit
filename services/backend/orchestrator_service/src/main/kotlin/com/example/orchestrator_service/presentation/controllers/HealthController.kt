package com.example.orchestrator_service.presentation.controllers

import com.example.orchestrator_service.presentation.http.Response
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/backend"])
class HealthController {

    @RequestMapping("/ping", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun ping(): ResponseEntity<Any> = runBlocking {
        ResponseEntity.status(200).body(Response(successfulOperation = true, data = null, code = 200))
    }
}