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

    @RequestMapping("/login", method=[RequestMethod.POST])
    @ResponseBody
    fun login(): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body("Login")
    }

    @RequestMapping("/register", method=[RequestMethod.POST])
    @ResponseBody
    fun register(): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body("Register")
    }

    @RequestMapping("/logout", method=[RequestMethod.POST])
    @ResponseBody
    fun logout(): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body("Log out")
    }

    @RequestMapping("/delete_user", method=[RequestMethod.DELETE])
    @ResponseBody
    fun deleteUser(): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body("Delete user")
    }
}