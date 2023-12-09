package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Payment
import com.example.foodservice.model.entity.PaymentEntity

object PaymentConverter: Converter<Payment, PaymentEntity> {
    override fun toDto(entity: PaymentEntity): Payment {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: Payment): PaymentEntity {
        TODO("Not yet implemented")
    }
}