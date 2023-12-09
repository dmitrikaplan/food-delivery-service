package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.CartDetail
import com.example.foodservice.web.dto.CartDetailDto

object CartDetailConverter: Converter<CartDetailDto, CartDetail> {
    override fun toDto(entity: CartDetail) = CartDetailDto(
        cartDetailId = entity.cartDetailId!!,
        cartId = entity.cart.cartId!!,
        foodId = entity.food.foodId!!,
        quantity = entity.quantity,
    )

    override fun toEntity(dto: CartDetailDto) = throw Error()
}