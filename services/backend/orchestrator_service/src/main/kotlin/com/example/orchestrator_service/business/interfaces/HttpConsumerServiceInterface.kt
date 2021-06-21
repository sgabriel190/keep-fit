package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*

abstract class HttpConsumerServiceInterface {
    val client = HttpClient(CIO){
        install(JsonFeature){
            serializer = JacksonSerializer()
        }
        expectSuccess = false
    }

    abstract suspend fun<T> executeRequest(block: suspend () -> T): T
}