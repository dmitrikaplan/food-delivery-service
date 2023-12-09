package com.example.domain.exception

class UserNotFoundException: RuntimeException() {

    override val message: String
        get() = "Пользователь не найден!"
}