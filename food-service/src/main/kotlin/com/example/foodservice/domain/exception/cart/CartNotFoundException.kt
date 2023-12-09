package com.example.foodservice.domain.exception.cart

class CartNotFoundException: RuntimeException() {

    override val message: String
        get() = "Корзина не найдена!"
}