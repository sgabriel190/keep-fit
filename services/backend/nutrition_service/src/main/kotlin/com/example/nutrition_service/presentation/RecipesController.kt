package com.example.nutrition_service.presentation

import com.example.nutrition_service.persistence.repositories.NutritionDAO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipes")
class RecipesController {
    @RequestMapping(value = ["/ping"], method = [RequestMethod.GET])
    fun ping(): ResponseEntity<Any>{
        val tmp = NutritionDAO()
        tmp.getMacronutrientsById(2)
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }
}