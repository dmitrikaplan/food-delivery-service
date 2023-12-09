package com.example.foodservice.repository

import com.example.foodservice.domain.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository: JpaRepository<Payment, Int> {
    fun findPaymentByPaymentId(paymentId: Int): Payment?
    fun findPaymentsByUserId(userId: Int): List<Payment>
}