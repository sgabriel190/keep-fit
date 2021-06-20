package com.example.user_service.presentation.controllers

import com.example.user_service.presentation.http.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users/ping")
class HealthController {
    @Async
    @RequestMapping("", method=[RequestMethod.GET])
    @ResponseBody
    fun ping(): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response(successfulOperation = true, code = 200, data = null))
    }
}