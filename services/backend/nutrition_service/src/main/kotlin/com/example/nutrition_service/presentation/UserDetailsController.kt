package com.example.nutrition_service.presentation

import com.example.nutrition_service.business.interfaces.UserDetailsServiceInterface
import com.example.nutrition_service.presentation.http.MyError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun getUserDetails(@PathVariable id: Int): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getUserDetails(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.POST])
    fun addUserDetails(@PathVariable id: Int,
                       @RequestParam(required = true) idActivityType: Int): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.addUserDetails(id, idActivityType)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }
}