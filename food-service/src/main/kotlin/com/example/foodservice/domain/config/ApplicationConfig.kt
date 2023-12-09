package com.example.foodservice.domain.config

import com.example.domain.config.DomainConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [DomainConfig::class])
class ApplicationConfig