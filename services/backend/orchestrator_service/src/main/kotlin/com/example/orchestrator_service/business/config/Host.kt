package com.example.orchestrator_service.business.config

data class Host(
    val uri: String
) {
    override fun toString(): String {
        return uri
    }
}
