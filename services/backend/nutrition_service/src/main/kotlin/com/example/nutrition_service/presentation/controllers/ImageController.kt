package com.example.nutrition_service.presentation.controllers

import com.example.nutrition_service.business.interfaces.ImageServiceInterface
import com.example.nutrition_service.presentation.http.MyError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.MediaTypeFactory
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/nutrition/image")
class ImageController {
    @Autowired
    lateinit var imageService: ImageServiceInterface

    @Async
    @RequestMapping(value = ["/{dirName}/{imageName}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getImage(@PathVariable dirName: String, @PathVariable imageName: String): ResponseEntity<Any?> {
        val response = imageService.getImage(dirName, imageName)
        return if (response.successfulOperation) {
            return ResponseEntity.status(response.code).contentType(
                MediaTypeFactory
                    .getMediaType(response.data)
                    .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(response.data)
        }
        else{
            ResponseEntity.status(response.code)
                .body(
                    MyError(
                        response.code,
                        response.error,
                        response.message)
                )
        }
    }
}