package com.example.nutrition_service.presentation.controllers

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.presentation.business_models.CreateMeal
import com.example.nutrition_service.presentation.http.MyError
import com.example.nutrition_service.presentation.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/nutrition")
class NutritionController {
    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @Async
    @RequestMapping(value = ["/ping"], method = [RequestMethod.GET])
    @ResponseBody
    fun ping(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(Response(successfulOperation = true, data = null, code = 200))
    }

    @Async
    @RequestMapping(value = ["/recipe/{id}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getRecipe(@PathVariable id: Int): ResponseEntity<Any> {
        val response = nutritionService.getRecipe(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/recipe/image/{idRecipe}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getImages(@PathVariable idRecipe: Int): ResponseEntity<Any> {
        val response = nutritionService.getImages(idRecipe)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/recipe/category"], method = [RequestMethod.GET])
    @ResponseBody
    fun getCategories(): ResponseEntity<Any> {
        val response = nutritionService.getCategories()
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/recipe/category/{id}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getCategory(@PathVariable id: Int): ResponseEntity<Any> {
        val response = nutritionService.getCategory(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/recipe"], method = [RequestMethod.GET])
    @ResponseBody
    fun getRecipesByParam(@RequestParam(required = false) categoryId: Int?,
                          @RequestParam(required = false) categoryName: String?,
                          @RequestParam(required = false) pagNumber: Int?,
                          @RequestParam(required = false) pagSize: Int?,
                          @RequestParam(required = false) calories: Int?): ResponseEntity<Any> {

        val response =  if (calories != null){
            nutritionService.getRecipeByCalories(calories, pagSize ?: 10)
        } else {
            if (categoryId != null) {
                nutritionService.getRecipeByCategoryId(categoryId, pag = pagNumber ?: 1, items = pagSize ?: 10)
            } else {
                if (categoryName != null) {
                    nutritionService.getRecipeByCategoryName(categoryName, pag = pagNumber ?: 1, items = pagSize ?: 10)
                } else {
                    nutritionService.getRecipes(pag = pagNumber ?: 1, items = pagSize ?: 10)
                }
            }
        }
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @Async
    @RequestMapping(value = ["/meal"], method = [RequestMethod.GET])
    @ResponseBody
    fun createMeal(@RequestBody data: CreateMeal): ResponseEntity<Any> {
        val response = nutritionService.createMeal(data)
        return if (response.successfulOperation){
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }
}