package com.example.authservice.domain.config

import com.example.domain.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {


    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain{

        return httpSecurity
            .csrf {
                it.disable()
            }
            .cors {
                it.disable()
            }
            .httpBasic{
                it.disable()
            }
            .authorizeHttpRequests {
                it.requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/auth/refresh")).authenticated()
                it.anyRequest().permitAll()
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()


    @Bean
    fun authenticationProvider(): AuthenticationProvider{
        return DaoAuthenticationProvider().also {
            it.setPasswordEncoder(passwordEncoder())
            it.setUserDetailsService(userDetailsService)
        }
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration)
    : AuthenticationManager =
        authenticationConfiguration.authenticationManager


}