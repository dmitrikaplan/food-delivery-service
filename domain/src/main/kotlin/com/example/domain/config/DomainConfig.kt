package com.example.domain.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.example.domain"])
@EntityScan(basePackages = ["com.example.domain"])
@ComponentScan(basePackages = ["com.example.domain"])
class DomainConfig