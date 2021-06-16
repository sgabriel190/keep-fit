package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.*
import io.ktor.client.features.json.*

abstract class HttpConsumerServiceInterface {
    val client = HttpClient{
        install(JsonFeature){
            serializer = JacksonSerializer()
        }
    }

    abstract suspend fun get(): Response<Any>
    abstract suspend fun post(): Response<Any>
    abstract suspend fun put(): Response<Any>
    abstract suspend fun delete(): Response<Any>
    abstract suspend fun patch(): Response<Any>
}