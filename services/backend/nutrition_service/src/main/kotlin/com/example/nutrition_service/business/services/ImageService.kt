package com.example.nutrition_service.business.services

import com.example.nutrition_service.business.interfaces.ImageServiceInterface
import com.example.nutrition_service.presentation.http.Response
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service

@Service
class ImageService: ImageServiceInterface {

    override fun getImage(dirName: String, imageName: String): Response<UrlResource> {
        try {
            val urlResource = UrlResource("file:images/$dirName/$imageName")
            return if (urlResource.exists()) {
                Response(successfulOperation = true, code = 200, data = urlResource)
            } else {
                Response(
                    successfulOperation = false,
                    code = 404,
                    data = urlResource,
                    error = "Error while searching for image.",
                    message = "No such image found."
                )
            }
        }
        catch (t: Throwable){
            return Response(successfulOperation = false, data = null, code = 400, error = t.toString())
        }
    }

    override fun createFile(uid: String, icon: ByteArray): String? {
        TODO("Not yet implemented")
    }

    override fun deleteFile(path: String) {
        TODO("Not yet implemented")
    }
}