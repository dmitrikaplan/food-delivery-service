package com.example.foodservice.domain.config

import com.example.domain.config.DomainConfig
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@Import(value = [DomainConfig::class])
@EnableJpaRepositories(basePackages = ["com.example.foodservice"])
@EntityScan(basePackages = ["com.example.foodservice"])
@ComponentScan(basePackages = ["com.example.foodservice"])
class ApplicationConfig