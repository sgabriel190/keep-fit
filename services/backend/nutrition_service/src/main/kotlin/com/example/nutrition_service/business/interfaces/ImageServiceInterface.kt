package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.presentation.http.Response
import org.springframework.core.io.UrlResource

interface ImageServiceInterface {
    fun getImage(dirName: String, imageName: String): Response<UrlResource>
    fun createFile(uid: String, icon: ByteArray): String?
    fun deleteFile(path: String)
}