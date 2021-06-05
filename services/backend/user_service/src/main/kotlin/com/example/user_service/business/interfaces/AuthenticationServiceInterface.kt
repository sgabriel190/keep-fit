package com.example.user_service.business.interfaces

import com.example.user_service.presentation.responses.HTTPResponse

interface AuthenticationServiceInterface {
    fun signIn(username: String?, password: String?): HTTPResponse
}