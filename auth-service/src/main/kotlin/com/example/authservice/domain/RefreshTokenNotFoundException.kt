package com.example.authservice.domain

class RefreshTokenNotFoundException: RuntimeException() {

    override val message: String
        get() = "Refresh token не найден!"
}