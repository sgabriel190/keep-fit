package com.example.user_service.business.security.jwt

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
class JwtProperties {
    val securityKey = "a-very-long-key-secret"

    val validity: Long = 3600000 // value in ms => 1 hour
}