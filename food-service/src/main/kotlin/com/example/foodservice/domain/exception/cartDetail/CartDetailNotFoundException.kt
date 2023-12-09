package com.example.foodservice.domain.exception.cartDetail

class CartDetailNotFoundException: RuntimeException() {

    override val message: String
        get() = "Деталь корзины не найдена!"
}