package com.example.authservice.web.model.dto

import org.hibernate.validator.constraints.Length

data class UserAuthDto(
    @Length(min = 6, message = "Username length must be greater than 5")
    val username: String,
    @Length(min = 6, message = "Password length must be greater than 5")
    val password: String
)