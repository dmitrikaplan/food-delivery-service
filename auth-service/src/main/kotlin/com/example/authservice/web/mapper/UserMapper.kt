package com.example.authservice.web.mapper

import com.example.domain.user.User
import com.example.authservice.web.model.dto.UserAuthDto
import com.example.authservice.web.model.dto.UserDto

fun UserDto.toEntity(): User =
    User().also {
        it.username = this.username
        it.password = this.password
        it.firstname = this.firstname
        it.lastname = this.lastname
        it.email = this.email
    }


fun UserAuthDto.toEntity(): User =
    User().also {
        it.username = this.username
        it.password = this.password
    }