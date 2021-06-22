package com.example.nutrition_service.presentation.controllers

import com.example.nutrition_service.business.interfaces.UserDetailsServiceInterface
import com.example.nutrition_service.presentation.business_models.CreateUserDetails
import com.example.nutrition_service.presentation.business_models.UpdateUserDetails
import com.example.nutrition_service.presentation.http.MyError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/nutrition/userDetails")
class UserDetailsController {
    @Autowired
    lateinit var userDetailsServiceInterface: UserDetailsServiceInterface

    @Async
    @RequestMapping(value = ["/activityType"], method = [RequestMethod.GET])
    @ResponseBody
    fun getActivityTypes(): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getActivityTypes()
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/gender"], method = [RequestMethod.GET])
    @ResponseBody
    fun getGenders(): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getGenders()
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/dietType"], method = [RequestMethod.GET])
    @ResponseBody
    fun getDietTypes(): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getDietTypes()
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/activityType/{id}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getActivityType(@PathVariable id: Int): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getActivityType(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getUserDetails(@PathVariable id: Int): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.getUserDetails(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = [""], method = [RequestMethod.POST])
    @ResponseBody
    fun addUserDetails(@RequestBody data: CreateUserDetails): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.addUserDetails(data)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    @ResponseBody
    fun deleteUserDetails(@PathVariable id: Int): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.deleteUserDetails(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(null)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.PATCH])
    @ResponseBody
    fun deleteUserDetails(@PathVariable id: Int,
                          @RequestBody data: UpdateUserDetails
    ): ResponseEntity<Any> {
        val response = userDetailsServiceInterface.updateUserDetails(id, data)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }
}