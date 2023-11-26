package com.example.authservice.web.model.dto

import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length

data class UserDto(
    @Length(min = 6, message = "Username length must be greater than 5")
    val username: String,
    val firstname: String,
    val lastname: String,
    @Length(min = 6, message = "Password length must be greater than 5")
    val password: String,
    @Email(message = "Email must fit the email pattern")
    val email: String
)
