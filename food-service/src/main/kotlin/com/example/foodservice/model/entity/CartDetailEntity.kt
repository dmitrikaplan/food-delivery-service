package com.example.foodservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "CartDetail")
class CartDetailEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cartDetailId: Int? = null
    @ManyToOne
    @JoinColumn(name = "cartId")
    lateinit var cart: CartEntity
    @ManyToOne
    @JoinColumn(name = "foodId")
    lateinit var food: FoodEntity
    var quantity: Int? = null
}