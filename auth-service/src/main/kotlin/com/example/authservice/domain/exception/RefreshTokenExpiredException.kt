package com.example.authservice.domain.exception

class RefreshTokenExpiredException: RuntimeException() {
    override val message: String
        get() = "Refresh token истек!"
}