package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Payment
import com.example.foodservice.web.dto.PaymentDto

object PaymentConverter: Converter<PaymentDto, Payment> {
    override fun toDto(entity: Payment) = PaymentDto(
        paymentId = entity.paymentId,
        userId = entity.user.id!!,
        cardNumber = entity.cardNumber!!.toInt(),
        expiryDate = entity.expireDate,
    )

    override fun toEntity(dto: PaymentDto) = throw Error()
}