package com.example.orchestrator_service.business.config

import io.ktor.client.request.*
import io.ktor.http.*

val HttpRequestBuilder.setBodyJson: (Any?) -> Unit?
    get() = {
            body: Any? ->
        if (body != null){
            contentType(ContentType.Application.Json)
            this.body = body
        }
    }

val HttpRequestBuilder.setQueryParams: (Map<String, Any>?) -> Unit?
    get() = {
            reqParam: Map<String, Any>? ->
        reqParam?.forEach{
            parameter(it.key, it.value)
        }
    }