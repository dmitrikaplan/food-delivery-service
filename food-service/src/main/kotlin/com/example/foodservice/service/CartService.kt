package com.example.foodservice.service

import org.springframework.stereotype.Service

@Service
interface CartService {
    fun create(username: String): Int
    fun delete(cartId: Int)
}