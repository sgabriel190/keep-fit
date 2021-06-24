package com.example.orchestrator_service.presentation.controllers.nutrition

import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.models.nutrition.request.UpdateUserDetailsRequest
import com.example.orchestrator_service.business.models.nutrition.request.UserDetailsRequest
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/nutrition"])
class UserDetailsController {

    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @RequestMapping("/gender", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getGenders(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getGenders(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/dietType", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getDietTypes(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getDietTypes(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/userDetails", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun addUserDetails(@RequestBody data: UserDetailsRequest,
                       @RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.addUserDetails(data, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/activityType", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getActivityTypes(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getActivityTypes(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/activityType/{id}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getActivityType(@PathVariable id: Int,
                        @RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getActivityType(id, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/userDetails/user", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getUserDetails(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getUserDetails(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/userDetails/user", method = [RequestMethod.PATCH])
    @ResponseBody
    @Async
    fun updateUserDetails(@RequestHeader(name="Authorization", required = false, defaultValue = "") token: String,
                          @RequestBody data: UpdateUserDetailsRequest): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.updateUserDetails(data, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

}