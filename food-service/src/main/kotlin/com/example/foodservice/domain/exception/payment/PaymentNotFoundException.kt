package com.example.foodservice.domain.exception.payment

class PaymentNotFoundException: RuntimeException() {

    override val message: String
        get() = "Способ оплаты не найден"
}