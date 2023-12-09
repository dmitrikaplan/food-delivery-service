package com.example.foodservice.model.dto

import java.time.LocalDateTime


data class Payment(
    val paymentId: Int,
    val userId: Int,
    val cardNumber: Int,
    val expiryDate: LocalDateTime
)
