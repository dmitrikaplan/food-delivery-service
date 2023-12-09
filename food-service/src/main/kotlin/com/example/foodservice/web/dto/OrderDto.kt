package com.example.foodservice.web.dto

import java.time.LocalDateTime

data class OrderDto(
    val orderId: Int,
    val userId: Int,
    val addressId: Int,
    val orderDate: LocalDateTime,
    val paymentId: Int,
    val cartId: Int,
    val fullPrice: Int,
    val state: OrderState
)