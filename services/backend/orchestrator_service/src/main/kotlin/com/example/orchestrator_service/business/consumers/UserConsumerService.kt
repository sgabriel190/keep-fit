package com.example.orchestrator_service.business.services

import com.example.orchestrator_service.business.interfaces.HttpConsumerServiceInterface
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.request.*
import org.springframework.stereotype.Service

@Service
class UserConsumerService: HttpConsumerServiceInterface() {

    private val host: String = "http://localhost:2020/api/users"

    override suspend fun get(path: String): Response<Any> {
        val response: Response<Any> =  client.get("$host/$path")
        return response
    }

    override suspend fun post(path: String): Response<Any> {
        val response: Response<Any> = client.post("$host/$path")
        return response
    }

    override suspend fun put(path: String): Response<Any> {
        val response: Response<Any> = client.put("$host/$path")
        return response
    }

    override suspend fun delete(path: String): Response<Any> {
        val response: Response<Any> = client.delete("$host/$path")
        return response
    }

    override suspend fun patch(path: String): Response<Any> {
        val response: Response<Any> = client.patch("$host/$path")
        return response
    }

}