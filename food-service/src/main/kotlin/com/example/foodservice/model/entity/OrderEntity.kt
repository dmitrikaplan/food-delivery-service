package com.example.foodservice.model.entity

import com.example.foodservice.model.dto.OrderState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "Order")
class OrderEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderId: Int? = null
    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit var user : UserEntity
    @ManyToOne
    @JoinColumn(name = "addressId")
    lateinit var address : AddressEntity
    lateinit var orderDate: LocalDateTime
    @ManyToOne
    @JoinColumn(name = "paymentId")
    lateinit var payment : PaymentEntity
    @ManyToOne
    @JoinColumn(name = "cartId")
    lateinit var cart : CartEntity
    var fullPrice : Int? = null
    @Enumerated(EnumType.STRING)
    lateinit var state: OrderState
}