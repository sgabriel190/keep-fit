package com.example.user_service.presentation.business_models

class LogoutRequest(
    val userId: Int
){
    constructor(): this(-1) {

    }
}
