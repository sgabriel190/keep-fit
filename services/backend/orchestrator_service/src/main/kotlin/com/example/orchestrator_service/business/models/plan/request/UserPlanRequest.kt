package com.example.orchestrator_service.business.models.plan.request

data class UserPlanRequest(
    val description: String,
    val planDays: Int,
    val menus: List<DailyMenuRequest>
)
