package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Order
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto

object OrderConverter: Converter<OrderDto, Order> {
    override fun toDto(entity: Order): OrderDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: OrderDto): Order {
        TODO("Not yet implemented")
    }
}