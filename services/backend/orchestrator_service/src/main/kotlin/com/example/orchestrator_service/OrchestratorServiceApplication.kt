package com.example.orchestrator_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrchestratorServiceApplication

fun main(args: Array<String>) {
	runApplication<OrchestratorServiceApplication>(*args)
}
