package com.example.foodservice.domain.exception.order

class OrderNotFoundException: RuntimeException() {

    override val message: String
        get() = "Заказ не найден!"
}