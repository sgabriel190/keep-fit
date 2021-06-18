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

    abstract suspend fun get(path: String, reqParam: Map<String, String>? = null): Response<Any>
    abstract suspend fun post(path: String, body: Any? = null, reqParam: Map<String, String>? = null): Response<Any>
    abstract suspend fun put(path: String, body: Any? = null, reqParam: Map<String, String>? = null): Response<Any>
    abstract suspend fun delete(path: String, body: Any? = null, reqParam: Map<String, String>? = null): Response<Any>
    abstract suspend fun patch(path: String, body: Any? = null, reqParam: Map<String, String>? = null): Response<Any>
}