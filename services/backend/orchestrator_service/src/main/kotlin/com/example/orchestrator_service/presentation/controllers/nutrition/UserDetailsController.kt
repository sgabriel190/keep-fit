package com.example.orchestrator_service.presentation.controllers.nutrition

import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.models.nutrition.CreateUserDetails
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
    fun getGenders(): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getGenders()
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/dietType", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getDietTypes(): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getDietTypes()
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/userDetails", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun addUserDetails(@RequestBody data: CreateUserDetails): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.addUserDetails(data)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/activityType", method = [RequestMethod.POST])
    @ResponseBody
    @Async
    fun getActivityTypes(): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getActivityTypes()
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/activityType/{id}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getActivityType(@PathVariable id: Int): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getActivityType(id)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/userDetails/{id}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getUserDetails(@PathVariable id: Int): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getUserDetails(id)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}