package com.example.orchestrator_service.business.interfaces

import com.example.orchestrator_service.presentation.http.MyError
import com.example.orchestrator_service.presentation.http.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.statement.*

abstract class HttpConsumerServiceInterface {
    val client = HttpClient{
        install(JsonFeature){
            serializer = GsonSerializer{
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        expectSuccess = false
    }

    suspend fun checkResponse(response: HttpResponse): Boolean{
        return if(response.status.value / 100 == 2){
            true
        } else {
            val tmp = response.receive<MyError>()
            throw Exception(tmp.error)
        }
    }
    abstract suspend fun<T> executeRequest(block: suspend () -> T): T
}