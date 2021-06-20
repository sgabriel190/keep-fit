package com.example.orchestrator_service.business.consumers

import com.example.orchestrator_service.business.config.Host
import com.example.orchestrator_service.business.config.setBodyJson
import com.example.orchestrator_service.business.config.setQueryParams
import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.springframework.stereotype.Component
import java.awt.Image

@Component
class NutritionConsumerService: HttpConsumerServiceInterface() {

    private val host = Host("http://localhost:2020/api/nutrition")

    override suspend fun get(path: String, reqParam: Map<String, Any>?): Response<Any> {
        val response: Response<Any> =  client.get("$host/$path"){
            this.setQueryParams(reqParam)
        }
        return response
    }

    suspend fun getImage(path: String): ByteArray {
        val response: ByteArray = client.get("$host/$path")
        return response
    }

    override suspend fun post(path: String, body: Any?, reqParam: Map<String, Any>?): Response<Any> {
        val response: Response<Any> =  client.post("$host/$path"){
            this.setBodyJson(body)
            this.setQueryParams(reqParam)
        }
        return response
    }

    override suspend fun put(path: String, body: Any?, reqParam: Map<String, Any>?): Response<Any> {
        val response: Response<Any> =  client.put("$host/$path"){
            this.setBodyJson(body)
            this.setQueryParams(reqParam)
        }
        return response
    }

    override suspend fun delete(path: String, body: Any?, reqParam: Map<String, Any>?): Response<Any> {
        val response: Response<Any> =  client.delete("$host/$path"){
            this.setBodyJson(body)
            this.setQueryParams(reqParam)
        }
        return response
    }

    override suspend fun patch(path: String, body: Any?, reqParam: Map<String, Any>?): Response<Any> {
        val response: Response<Any> =  client.patch("$host/$path"){
            this.setBodyJson(body)
            this.setQueryParams(reqParam)
        }
        return response
    }
}