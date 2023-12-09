package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Cart
import com.example.foodservice.model.entity.CartEntity

object CartConverter: Converter<Cart, CartEntity> {
    override fun toDto(entity: CartEntity): Cart {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: Cart): CartEntity {
        TODO("Not yet implemented")
    }
}