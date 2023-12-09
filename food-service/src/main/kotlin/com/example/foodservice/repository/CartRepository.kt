package com.example.foodservice.repository

import com.example.foodservice.model.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: JpaRepository<CartEntity, Int> {

}