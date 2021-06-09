package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.persistence.pojos.ActivityTypeModel
import com.example.nutrition_service.persistence.pojos.UserDetailModel
import com.example.nutrition_service.presentation.http.Response

interface UserDetailsServiceInterface {
    fun getActivityTypes(): Response<List<ActivityTypeModel>>
    fun getActivityType(id: Int): Response<ActivityTypeModel>
    fun getUserDetails(id: Int): Response<UserDetailModel>
    fun addUserDetails(id: Int, idActivityType: Int): Response<Any>
}