package com.example.authservice.domain.config

import com.example.authservice.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
class ApplicationConfig(
    private val userRepository: UserRepository
) {
    @Bean
    fun userDetailsService(): UserDetailsService{
        return UserDetailsService {
              username -> userRepository.findUserByUsername(username)
                    ?: throw UsernameNotFoundException("Пользователь не найден")
        }
    }
}