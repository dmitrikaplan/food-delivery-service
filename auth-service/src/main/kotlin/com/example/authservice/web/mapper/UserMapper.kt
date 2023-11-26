package com.example.authservice.web.mapper

import com.example.authservice.domain.user.User
import com.example.authservice.web.model.dto.UserAuthDto
import com.example.authservice.web.model.dto.UserDto

fun UserDto.toEntity(): User =
    User(
        username = this.username,
        password = this.password,
    ).also {
        it.firstname = this.firstname
        it.lastname = this.lastname
        it.email = this.email
    }


fun UserAuthDto.toEntity(): User =
    User(
        username = this.username,
        password = this.password
    )