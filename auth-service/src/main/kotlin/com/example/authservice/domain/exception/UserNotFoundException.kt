package com.example.authservice.domain.exception

class UserNotFoundException: RuntimeException() {

    override val message: String
        get() = "Пользователь не найден!"
}