package com.example.nutrition_service.business.interfaces

import com.example.nutrition_service.persistence.pojos.ActivityTypeModel
import com.example.nutrition_service.persistence.pojos.UserDetailModel
import com.example.nutrition_service.presentation.business_models.CreateUserDetails
import com.example.nutrition_service.presentation.business_models.UpdateUserDetails
import com.example.nutrition_service.presentation.http.Response

interface UserDetailsServiceInterface {
    fun getActivityTypes(): Response<List<ActivityTypeModel>>
    fun getActivityType(id: Int): Response<ActivityTypeModel>
    fun getUserDetails(id: Int): Response<UserDetailModel>
    fun addUserDetails(data: CreateUserDetails): Response<Any>
    fun deleteUserDetails(id: Int): Response<Any>
    fun updateUserDetails(id: Int, data: UpdateUserDetails): Response<Any>

    fun getGenders(): Response<Any>
    fun getDietTypes(): Response<Any>
}