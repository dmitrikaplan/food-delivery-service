package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.CartDetail
import com.example.foodservice.web.dto.CartDetailDto

object CartDetailConverter: Converter<CartDetailDto, CartDetail> {
    override fun toDto(entity: CartDetail): CartDetailDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: CartDetailDto): CartDetail {
        TODO("Not yet implemented")
    }
}