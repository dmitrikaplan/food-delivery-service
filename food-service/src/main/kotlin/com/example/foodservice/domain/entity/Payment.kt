package com.example.foodservice.domain.entity

import com.example.domain.user.User
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
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var paymentId: Int? = null

    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit var user: User

    var cardNumber: Long? = null
    lateinit var expireDate: LocalDateTime
}