package com.example.orchestrator_service.business.models.nutrition.response

data class RecipeLiteResponse(
    val id: Int,
    val timeTotal: TimeTotalResponse,
    val name: String,
    val description: String,
    val keywords: String,
    val categories: List<CategoryResponse>
)
