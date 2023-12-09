package com.example.domain.exception

class UserAlreadyRegisteredException: RuntimeException() {

    override val message: String
        get() = "Пользователь уже зарегистрирован!"
}