package com.example.orchestrator_service.presentation.http

import java.text.SimpleDateFormat
import java.util.*

data class MyError(
    val code: Int,
    val error: String,
    val info: String,
    val timestamp: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
)
