package com.example.orchestrator_service.business.models.user.response

import com.example.orchestrator_service.business.models.nutrition.response.UserDetailResponse

data class UserProfileResponse(
    val id: Int,
    val username: String,
    val email: String,
    val userDetails: UserDetailResponse
)

