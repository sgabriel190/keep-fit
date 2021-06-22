package com.example.orchestrator_service.business.models.nutrition.response

data class UserDetailResponse(
    val age: Int,
    val height: Int,
    val weight: Int,
    val idealWeight: Int,
    val calories: Int,
    val bmi: Float,
    val wnd: Int,
    val activityType: ActivityTypeResponse,
    val gender: GenderResponse,
    val dietType: DietTypeResponse
)
