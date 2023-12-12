package com.example.foodservice.web.dto

import com.example.foodservice.web.validation.OnUpdate
import jakarta.validation.constraints.NotNull


data class CartDetailDto(
    @NotNull(groups = [OnUpdate::class])
    val cartDetailId: Int?,
    val cartId: Int,
    val foodId: Int,
    val quantity: Int
)
