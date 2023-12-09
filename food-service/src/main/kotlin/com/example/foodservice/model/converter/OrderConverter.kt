package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Order
import com.example.foodservice.model.entity.OrderEntity

object OrderConverter: Converter<Order, OrderEntity> {
    override fun toDto(entity: OrderEntity): Order {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: Order): OrderEntity {
        TODO("Not yet implemented")
    }
}