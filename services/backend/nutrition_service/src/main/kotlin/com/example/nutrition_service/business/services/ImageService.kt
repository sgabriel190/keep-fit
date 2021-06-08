package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.ImageServiceInterface
import com.example.nutrition_service.presentation.http.Response
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service

@Service
class ImageService: ImageServiceInterface {

    override fun getImage(dirName: String, imageName: String): Response<UrlResource> {
        val urlResource = UrlResource("file:images/$dirName/$imageName")
        return if (urlResource.exists()) {
            Response(successfulOperation = true, code = 200, data = urlResource)
        }
        else {
            Response(successfulOperation = true, code = 404, data = urlResource, error = "error on image", message = "No such file")
        }
    }

    override fun createFile(uid: String, icon: ByteArray): String? {
        TODO("Not yet implemented")
    }

    override fun deleteFile(path: String) {
        TODO("Not yet implemented")
    }
}