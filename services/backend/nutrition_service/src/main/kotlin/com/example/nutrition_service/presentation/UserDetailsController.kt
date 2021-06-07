package com.example.nutrition_service.presentation

import com.example.nutrition_service.business.interfaces.UserDetailsServiceInterface
import com.example.nutrition_service.presentation.http.MyError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/nutrition/userDetails")
class UserDetailsController {
    @Autowired
    lateinit var userDetailsServiceInterface: UserDetailsServiceInterface

    @RequestMapping(value = ["/activityType"], method = [RequestMethod.GET])
    fun getActivityTypes(): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getActivityTypes()
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/activityType/{id}"], method = [RequestMethod.GET])
    fun getActivityType(@PathVariable id: Int): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getActivityType(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }
}