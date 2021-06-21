package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.statement.*

abstract class HttpConsumerServiceInterface {
    val client = HttpClient(CIO){
        install(JsonFeature){
            serializer = JacksonSerializer()
        }
        expectSuccess = false
    }

    suspend fun checkResponse(response: HttpResponse): Response<out Any> {
        return if(response.status.value / 100 == 2){
            val tmp = response.receive<Response<Any>>()
            tmp
        } else {
            val tmp = response.receive<MyError>()
            Response(successfulOperation = false, code = tmp.code, data = tmp)
        }
    }
    abstract suspend fun<T> executeRequest(block: suspend () -> T): T
}