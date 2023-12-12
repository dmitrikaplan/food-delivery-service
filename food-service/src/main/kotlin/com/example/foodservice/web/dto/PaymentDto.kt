package com.example.foodservice.web.dto

import java.time.LocalDateTime


data class PaymentDto(
    val paymentId: Int?,
    val userId: Int,
    val cardNumber: Int,
    val expiryDate: LocalDateTime
)
