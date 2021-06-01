package com.example.user_service.business.security.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.lang.Exception
import kotlin.jvm.Throws

class JwtSecurityConfigurer(private val jwtTokenProvider: JwtTokenProvider): SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    @Throws(Exception::class)
    override fun configure(builder: HttpSecurity?) {
        val customFilter = JwtTokenAuthenticationFilter(jwtTokenProvider)
        builder?.exceptionHandling()
            ?.authenticationEntryPoint(JwtAuthenticationEntryPoint())
            ?.and()
            ?.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}