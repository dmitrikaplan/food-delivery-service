package com.example.foodservice.service.impl

import com.example.domain.exception.UserNotFoundException
import com.example.domain.repository.UserRepository
import com.example.foodservice.domain.entity.Payment
import com.example.foodservice.repository.FoodRepository
import com.example.foodservice.repository.PaymentRepository
import com.example.foodservice.service.PaymentService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentServiceImpl(
    private val paymentRepository: PaymentRepository,
    private val userRepository: UserRepository,
) : PaymentService {
    @Transactional
    override fun createPayment(userId: Int, cartNumber: Int, expireDate: LocalDateTime) = paymentRepository.save(
        Payment().apply {
            this.user = userRepository.findUserById(userId) ?: throw UserNotFoundException()
            this.paymentId = paymentId
            this.cardNumber = cardNumber
            this.expireDate = expireDate
        }
    ).paymentId!!

    override fun getPaymentsByUserId(userId: Int): List<Payment> = paymentRepository.findPaymentsByUserId(userId)
}