package com.example.foodservice.web.dto


data class CartDetailDto(
    val cartDetailId: Int,
    val cartId: Int,
    val foodId: Int,
    val quantity: Int
)
