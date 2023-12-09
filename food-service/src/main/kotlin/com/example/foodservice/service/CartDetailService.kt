package com.example.foodservice.service

import com.example.foodservice.domain.entity.CartDetail
import org.springframework.stereotype.Service

@Service
interface CartDetailService {

    fun save(cartId: Int, foodId: Int, quantity: Int): Int
    fun delete(cartDetailId: Int)
    fun update(cartDetailId: Int, quantity: Int)

}