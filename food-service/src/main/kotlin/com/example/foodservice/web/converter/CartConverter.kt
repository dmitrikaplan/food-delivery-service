package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Cart
import com.example.foodservice.web.dto.CartDto

object CartConverter: Converter<CartDto, Cart> {
    override fun toDto(entity: Cart): CartDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: CartDto): Cart {
        TODO("Not yet implemented")
    }
}