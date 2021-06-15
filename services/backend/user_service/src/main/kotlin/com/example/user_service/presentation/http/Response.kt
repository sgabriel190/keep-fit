package com.example.user_service.presentation.http

data class Response<T>(
    val successfulOperation: Boolean,
    val code: Int,
    val data: T?,
    val error: String = "",
    val message: String = ""
)
