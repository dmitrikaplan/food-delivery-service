package com.example.foodservice.repository

import com.example.foodservice.model.entity.AddressEntity
import com.example.foodservice.model.entity.CartDetailEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartDetailRepository: JpaRepository<CartDetailEntity, Int> {

}