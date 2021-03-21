package com.example.user_service.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/users")
class UserController {

    @RequestMapping("/login", method=[RequestMethod.GET])
    @ResponseBody
    fun login(): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body("Login")
    }
}