package com.example.user_service.business.security.jwt

import com.example.user_service.presentation.responses.HTTPResponse
import com.google.gson.Gson
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class JwtTokenAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        try{
            val token = jwtTokenProvider.resolveToken((request as HttpServletRequest))
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val auth = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }
            chain?.doFilter(request, response)
        } catch (e: InvalidJwtAuthenticationException){
            val resp = response as HttpServletResponse
            resp.contentType = "application/json"
            resp.characterEncoding = "UTF-8"
            val text = Gson().toJson(HTTPResponse(false, 401, { "message" to "Provided Information is Invalid. JWT is invalid/expired. Request a new JWT, maybe :)"}))
            resp.addHeader("SC_UNAUTHORIZED", "Provided Information is Invalid");
            resp.status = 401
            resp.writer.print(text)
            resp.writer.close()
        }
    }

}