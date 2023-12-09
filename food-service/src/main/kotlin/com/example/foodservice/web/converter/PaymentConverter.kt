package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Payment
import com.example.foodservice.web.dto.PaymentDto

object PaymentConverter: Converter<PaymentDto, Payment> {
    override fun toDto(entity: Payment): PaymentDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: PaymentDto): Payment {
        TODO("Not yet implemented")
    }
}