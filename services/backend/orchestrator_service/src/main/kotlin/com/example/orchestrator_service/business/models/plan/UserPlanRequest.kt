package com.example.orchestrator_service.business.models.plan

data class UserPlanRequest(
    val description: String,
    val planDays: Int,
    val menus: List<DailyMenuRequest>
)
