package com.example.foodservice.model.dto


data class CartDetail(
    val cartDetailId: Int,
    val cartId: Int,
    val foodId: Int,
    val quantity: Int
)
