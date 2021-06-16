package com.example.user_service.business.security.jwt

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
class JwtProperties {
    val secretKey = "dsadnaQWDADPQWdqob231uxp!@151213!!!@#!@#i12312QA@!#1!"

    //validity in milliseconds
    val validityInMs: Long = 3600000 // 1h
}