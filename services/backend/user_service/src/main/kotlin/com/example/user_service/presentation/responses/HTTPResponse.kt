package com.example.user_service.presentation.responses

data class HTTPResponse(
    val success: Boolean,
    val code: Int,
    val data: Any? = {}
)
