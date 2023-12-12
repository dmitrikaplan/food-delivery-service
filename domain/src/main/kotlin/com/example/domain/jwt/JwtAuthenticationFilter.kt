package com.example.domain.jwt

import com.example.domain.repository.UserRepository
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
import kotlin.RuntimeException

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userRepository: UserRepository
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
        log.error("username is $username")

        if(SecurityContextHolder.getContext().authentication == null){
            try{
                val userDetails = userRepository.findUserByUsername(username)
                    ?: throw RuntimeException("User not found!!")
                if(jwtService.isAccessTokenValid(jwtToken)){
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            } catch (e: RuntimeException){
                log.error(e.message)
            }
        }

        filterChain.doFilter(request, response)
    }
}