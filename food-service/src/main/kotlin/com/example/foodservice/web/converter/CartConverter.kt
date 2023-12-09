package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Cart
import com.example.foodservice.web.dto.CartDto

object CartConverter: Converter<CartDto, Cart> {
    override fun toDto(entity: Cart) = CartDto(
        cartId = entity.cartId!!,
        userId = entity.user.id!!
    )
    override fun toEntity(dto: CartDto) = throw Error()
}