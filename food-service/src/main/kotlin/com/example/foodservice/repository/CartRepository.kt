package com.example.foodservice.repository

import com.example.foodservice.domain.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: JpaRepository<Cart, Int> {

}