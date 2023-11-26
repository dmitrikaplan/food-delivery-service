package com.example.authservice.domain.exception

class UserAlreadyRegisteredException: RuntimeException() {

    override val message: String
        get() = "Пользователь уже зарегистрирован!"
}