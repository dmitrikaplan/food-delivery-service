package com.example.foodservice.service

import com.example.foodservice.domain.entity.Payment
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
interface PaymentService {
    fun createPayment(userId: Int, cartNumber: Int, expireDate: LocalDateTime) : Int

    fun getPaymentsByUserId(userId: Int) : List<Payment>
}