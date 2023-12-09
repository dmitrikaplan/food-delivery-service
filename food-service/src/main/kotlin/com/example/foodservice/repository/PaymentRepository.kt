package com.example.foodservice.repository

import com.example.foodservice.model.entity.CategoryEntity
import com.example.foodservice.model.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository: JpaRepository<PaymentEntity, Int> {

}