package com.example.foodservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "Cart")
class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cartId: Int? = null
    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit val user: UserEntity // fixme

}