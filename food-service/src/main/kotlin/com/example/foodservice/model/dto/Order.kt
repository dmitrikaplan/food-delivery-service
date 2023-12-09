package com.example.foodservice.model.dto

import java.time.LocalDateTime

data class Order(
    val orderId: Int,
    val userId: Int,
    val addressId: Int,
    val orderDate: LocalDateTime,
    val paymentId: Int,
    val cartId: Int,
    val fullPrice: Int,
    val state: OrderState
)