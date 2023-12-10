package com.example.authservice.domain.exception

class RefreshTokenNotFoundException: RuntimeException() {

    override val message: String
        get() = "Refresh token не найден!"
}