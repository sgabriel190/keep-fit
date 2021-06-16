package com.example.user_service.business.security

import com.example.user_service.persistence.interfaces.UserRepositoryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService: UserDetailsService {
    @Autowired
    private lateinit var users: UserRepositoryInterface

    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let { users.getByUsername(it) }
            ?: throw Exception("Username is null.")
    }
}