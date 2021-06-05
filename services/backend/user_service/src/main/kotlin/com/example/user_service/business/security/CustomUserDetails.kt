package com.example.user_service.business.security

import com.example.user_service.persistence.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetails: UserDetailsService {
    @Autowired
    private lateinit var usersRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails? {
        return username?.let { usersRepository.getByUsername(it) ?: throw Exception("Username not found") }
    }
}