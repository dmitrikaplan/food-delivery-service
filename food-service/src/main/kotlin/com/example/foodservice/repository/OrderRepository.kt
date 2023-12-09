package com.example.foodservice.repository

import com.example.foodservice.model.entity.CategoryEntity
import com.example.foodservice.model.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<OrderEntity, Int> {

}