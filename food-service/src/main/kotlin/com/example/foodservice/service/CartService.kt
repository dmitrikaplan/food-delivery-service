package com.example.foodservice.service

import org.springframework.stereotype.Service

@Service
interface CartService {
    fun create(userId: Int): Int
}