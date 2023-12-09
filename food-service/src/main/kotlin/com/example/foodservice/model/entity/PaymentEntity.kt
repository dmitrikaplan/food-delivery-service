package com.example.foodservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "Payment")
class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var paymentId: Long? = null
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit var user: UserEntity
    var cardNumber: Long? = null
    lateinit var expireDate: LocalDateTime
}