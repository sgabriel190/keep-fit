package com.example.nutrition_service.presentation

import com.example.nutrition_service.business.interfaces.NutritionServiceInterface
import com.example.nutrition_service.persistence.repositories.NutritionDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/nutrition")
class RecipesController {
    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @RequestMapping(value = ["/ping"], method = [RequestMethod.GET])
    fun ping(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }
}