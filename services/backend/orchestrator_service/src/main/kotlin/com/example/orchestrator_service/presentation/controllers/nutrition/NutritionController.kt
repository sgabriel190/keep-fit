package com.example.orchestrator_service.presentation.controllers.nutrition

import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.business.models.nutrition.CreateMeal
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/nutrition"])
class NutritionController {

    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @RequestMapping("/recipe/{id}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getRecipe(@PathVariable id: Int,
                  @RequestHeader(name="Authorization") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getRecipe(id, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
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
                   @RequestParam(required = false) calories: Int?,
                   @RequestHeader(name="Authorization") token: String): ResponseEntity<Any> = runBlocking {
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
        val result = nutritionService.getRecipes(tmp, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/recipe/category", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getCategories(@RequestHeader(name="Authorization") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getCategories(token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("/recipe/category/{id}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getCategory(@PathVariable id: Int,
                    @RequestHeader(name="Authorization") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getCategory(id, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }

    @RequestMapping("meal", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getMeal(@RequestBody data: CreateMeal,
                @RequestHeader(name="Authorization") token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.createMeal(data, token)
        if (result.successfulOperation){
            ResponseEntity.status(result.code).body(result)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}