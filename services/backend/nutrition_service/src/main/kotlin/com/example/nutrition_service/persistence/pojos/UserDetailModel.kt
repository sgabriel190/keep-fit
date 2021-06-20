package com.example.nutrition_service.persistence.pojos

data class UserDetailModel(
    val age: Int,
    val height: Int,
    val weight: Int,
    val idealWeight: Int,
    val calories: Int,
    val bmi: Float,
    val wnd: Int,
    val activityType: ActivityTypeModel,
    val gender: GenderModel,
    val dietType: DietTypeModel
)