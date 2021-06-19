package com.example.orchestrator_service.presentation.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

@Component
class CORSFilter: Filter {
    private val log: Logger = LoggerFactory.getLogger(CORSFilter::class.java)
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse

        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"))
        res.setHeader("Access-Control-Allow-Credentials", "true")
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT")
        res.setHeader("Access-Control-Max-Age", "3600")
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me")
        chain!!.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig?) {

    }

    override fun destroy() {

    }

    init {
        log.info("CORSFilter init.")
    }
}