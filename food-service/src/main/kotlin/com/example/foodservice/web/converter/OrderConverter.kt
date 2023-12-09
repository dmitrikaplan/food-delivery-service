package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Order
import com.example.foodservice.web.dto.OrderDto
import java.lang.Error

object OrderConverter: Converter<OrderDto, Order> {
    override fun toDto(entity: Order): OrderDto =
        OrderDto(
            orderId = entity.orderId!!,
            userId = entity.user.id!!,
            addressId = entity.address.addressId!!,
            orderDate = entity.orderDate,
            paymentId = entity.payment.paymentId!!,
            cartId = entity.cart.cartId!!,
            fullPrice = entity.fullPrice,
            state = entity.state
        )

    override fun toEntity(dto: OrderDto) = throw Error()
}