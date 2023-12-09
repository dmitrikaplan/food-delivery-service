package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.CartDetail
import com.example.foodservice.model.entity.CartDetailEntity

object CartDetailConverter: Converter<CartDetail, CartDetailEntity> {
    override fun toDto(entity: CartDetailEntity): CartDetail {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: CartDetail): CartDetailEntity {
        TODO("Not yet implemented")
    }
}