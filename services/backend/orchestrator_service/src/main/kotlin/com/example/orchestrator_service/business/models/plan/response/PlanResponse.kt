package com.example.orchestrator_service.business.models.plan.response

data class PlanResponse(
    val idUser: Int,
    val planDays: Int,
    val menus: List<MenuResponse>,
    val description: String
)
