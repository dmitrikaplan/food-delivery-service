package com.example.foodservice.domain.entity

import com.example.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "Cart")
class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cartId: Int? = null

    @OneToOne
    @JoinColumn(name = "userId")
    lateinit var user: User

}