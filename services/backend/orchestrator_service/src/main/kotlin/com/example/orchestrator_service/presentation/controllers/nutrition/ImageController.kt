package com.example.orchestrator_service.presentation.controllers.nutrition

import com.example.orchestrator_service.business.interfaces.NutritionServiceInterface
import com.example.orchestrator_service.presentation.http.MyError
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/backend/nutrition/image"])
class ImageController {
    @Autowired
    lateinit var nutritionService: NutritionServiceInterface

    @RequestMapping("/{imgDir}/{imgName}", method = [RequestMethod.GET])
    @ResponseBody
    @Async
    fun getImage(@PathVariable imgDir: String,
                 @PathVariable imgName: String,
                 @RequestHeader(name="Authorization", required = false) token: String): ResponseEntity<Any> = runBlocking {
        val result = nutritionService.getImage("$imgDir/$imgName", token)
        if (result.successfulOperation){
            ResponseEntity.status(200)
                .contentType(MediaType.IMAGE_JPEG)
                .body(result.data)
        } else {
            ResponseEntity.status(result.code).body(MyError(code = result.code, error = result.error, info = result.message))
        }
    }
}