package com.example.orchestrator_service.business.models.plan.response

data class PlanModelResponse(
    val idUser: Int,
    val planDays: Int,
    val menus: List<MenuModelResponse>,
    val description: String
)
