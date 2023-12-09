package com.example.foodservice.repository

import com.example.foodservice.domain.entity.CartDetail
import com.example.foodservice.service.impl.CartDetailServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartDetailRepository: JpaRepository<CartDetail, Int> {

    fun findCartDetailByCartDetailId(cartDetailId: Int): CartDetail?

}