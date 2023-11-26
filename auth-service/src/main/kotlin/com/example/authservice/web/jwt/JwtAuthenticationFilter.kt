package com.example.authservice.web.jwt

import com.example.authservice.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
): OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(javaClass)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if(header.isNullOrBlank() || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken = header.substring(7)
        val username = jwtService.extractUsernameFromRefreshToken(jwtToken)

        if(SecurityContextHolder.getContext().authentication == null){
            try{
                val userDetails = userDetailsService.loadUserByUsername(username)
                if(jwtService.isAccessTokenValid(jwtToken)){
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            } catch (e: UsernameNotFoundException){
                log.error(e.message)
            }
        }

        filterChain.doFilter(request, response)
    }
}