package com.example.foodservice.service


import com.example.foodservice.domain.entity.Order
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
interface OrderService {

    fun save(
        userId: Int,
        addressId: Int,
        orderDate: LocalDateTime,
        paymentId: Int,
        cartId: Int,
    ): Int

    fun getOrder(orderId: Int): Order
}