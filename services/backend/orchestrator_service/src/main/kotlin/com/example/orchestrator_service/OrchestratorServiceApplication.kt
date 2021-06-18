package com.example.orchestrator_service

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrchestratorServiceApplication

fun main(args: Array<String>): Unit = runBlocking {
	runApplication<OrchestratorServiceApplication>(*args)
}
