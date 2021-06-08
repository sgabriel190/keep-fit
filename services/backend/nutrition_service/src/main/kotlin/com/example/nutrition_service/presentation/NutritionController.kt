package com.example.nutrition_service.presentation

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.persistence.repositories.NutritionDAO
import com.example.nutrition_service.presentation.http.MyError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam


@RestController
@RequestMapping("/api/nutrition")
class NutritionController {
    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @RequestMapping(value = ["/ping"], method = [RequestMethod.GET])
    fun ping(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    @RequestMapping(value = ["/instruction/{id}"], method = [RequestMethod.GET])
    fun getInstruction(@PathVariable id: Int): ResponseEntity<Any> {
        val response = nutritionService.getInstruction(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/recipe/instruction/{idRecipe}"], method = [RequestMethod.GET])
    fun getInstructions(@PathVariable idRecipe: Int): ResponseEntity<Any> {
        val response = nutritionService.getInstructions(idRecipe)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/recipe/{id}"], method = [RequestMethod.GET])
    fun getRecipe(@PathVariable id: Int): ResponseEntity<Any> {
        val response = nutritionService.getRecipe(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/recipe/image/{idRecipe}"], method = [RequestMethod.GET])
    fun getImages(@PathVariable idRecipe: Int): ResponseEntity<Any> {
        val response = nutritionService.getImages(idRecipe)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/recipe/category"], method = [RequestMethod.GET])
    fun getCategories(): ResponseEntity<Any> {
        val response = nutritionService.getCategories()
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/recipe/category/{id}"], method = [RequestMethod.GET])
    fun getCategory(@PathVariable id: Int): ResponseEntity<Any> {
        val response = nutritionService.getCategory(id)
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }

    @RequestMapping(value = ["/recipe"], method = [RequestMethod.GET])
    fun getRecipesByParam(@RequestParam(required = false) categoryId: Int?,
                          @RequestParam(required = false) categoryName: String?,
                          @RequestParam(required = false) pagNumber: Int?,
                          @RequestParam(required = false) pagSize: Int?): ResponseEntity<Any> {
        val response =  if(categoryId != null){
            nutritionService.getRecipeByCategoryId(categoryId)
        }
        else {
            if(categoryName != null){
                nutritionService.getRecipeByCategoryName(categoryName)
            }
            else{
                nutritionService.getRecipes(pag = pagNumber ?: 1, items = pagSize ?: 10)
            }
        }
        return if (response.successfulOperation) {
            ResponseEntity.status(response.code).body(response)
        } else {
            ResponseEntity.status(response.code).body(MyError(response.code, response.error, response.message))
        }
    }
}