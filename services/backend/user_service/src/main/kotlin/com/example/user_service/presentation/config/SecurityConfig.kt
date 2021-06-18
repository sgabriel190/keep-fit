package com.example.user_service.presentation.config

import com.example.user_service.business.security.jwt.JwtSecurityConfigurer
import com.example.user_service.business.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return MessageDigestPasswordEncoder("SHA-256")

        // return PasswordEncoderFactories.createDelegatingPasswordEncoder()
        // return NoOpPasswordEncoder.getInstance()
        // val passEncoder = StandardPasswordEncoder()
        // return passEncoder
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/users/ping", "/api/users/auth/login", "/api/users/auth/register").permitAll()
            //comment these and uncomment below for jwt guard on all routes
            //.antMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
            //.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("USER")
            //.antMatchers(HttpMethod.POST, "/api/**").hasRole("USER")
            //.antMatchers(HttpMethod.PUT, "/api/**").hasRole("USER")
            //.antMatchers(HttpMethod.GET, "/auth/me").hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .apply(JwtSecurityConfigurer(jwtTokenProvider))
    }
}