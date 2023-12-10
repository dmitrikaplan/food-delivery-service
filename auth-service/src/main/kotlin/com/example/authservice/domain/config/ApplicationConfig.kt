package com.example.authservice.domain.config

import com.example.domain.repository.UserRepository
import com.example.domain.config.DomainConfig
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
@Import(value = [DomainConfig::class])
@EnableJpaRepositories(value = ["com.example.authservice"])
@EntityScan(value = ["com.example.authservice"])
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