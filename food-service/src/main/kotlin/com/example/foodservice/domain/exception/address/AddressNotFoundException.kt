package com.example.foodservice.domain.exception.address

class AddressNotFoundException: RuntimeException() {

    override val message: String
        get() = "Адрес не найден!"
}