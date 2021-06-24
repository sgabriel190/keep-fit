package com.example.orchestrator_service.business.config

data class Host(
    val host: String,
    val port: String,
    val path: String
) {
    override fun toString(): String {
        return "$host:$port/$path"
    }
}
