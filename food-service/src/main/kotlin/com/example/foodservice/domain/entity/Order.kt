package com.example.foodservice.domain.entity

import com.example.domain.user.User
import com.example.foodservice.web.dto.OrderState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "Orber")
class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderId: Int? = null

    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit var user : User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    lateinit var address : Address

    lateinit var orderDate: LocalDateTime

    @ManyToOne(optional = true)
    @JoinColumn(name = "paymentId")
    lateinit var payment : Payment

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    lateinit var cart : Cart

    var fullPrice : Int = 0

    @Enumerated(EnumType.STRING)
    lateinit var state: OrderState
}