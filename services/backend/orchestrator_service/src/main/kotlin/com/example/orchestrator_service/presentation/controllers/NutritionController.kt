package com.example.orchestrator_service.presentation.controllers

import com.example.orchestrator_service.business.interfaces.OrchestratorServiceInterface
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/nutrition"])
class NutritionController {
    @Autowired
    lateinit var orchestratorService: OrchestratorServiceInterface

    @RequestMapping("/image/{imgDir}/{imgName}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getImage(@PathVariable imgDir: String,
                 @PathVariable imgName: String): ResponseEntity<Any> = runBlocking {
        val result = orchestratorService.getImage("$imgDir/$imgName")
        if (result.successfulOperation){
            ResponseEntity.status(200)
                .contentType(MediaType.IMAGE_JPEG)
                .body(result.data)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/recipe", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getRecipes(@RequestParam(required = false) categoryId: Int?,
                   @RequestParam(required = false) categoryName: String?,
                   @RequestParam(required = false) pagNumber: Int?,
                   @RequestParam(required = false) pagSize: Int?,
                   @RequestParam(required = false) calories: Int?): ResponseEntity<Any> = runBlocking {
        val params = mutableMapOf<String, Any?>(
            "categoryId" to categoryId,
            "categoryName" to categoryName,
            "pagNumber" to pagNumber,
            "pagSize" to pagSize,
            "calories" to calories
        )
        val tmp = params.filter {
            it.value != null
        }.map {
            it.key to it.value!!
        }.toMap()
        val result = orchestratorService.getRecipes(tmp)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/gender", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getGenders(): ResponseEntity<Any> = runBlocking {
        val result = orchestratorService.getGenders()
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
        val result = orchestratorService.getDietTypes()
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/activityType", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getActivityTypes(): ResponseEntity<Any> = runBlocking {
        val result = orchestratorService.getActivityTypes()
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
        val result = orchestratorService.getActivityType(id)
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
        val result = orchestratorService.getUserDetails(id)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}