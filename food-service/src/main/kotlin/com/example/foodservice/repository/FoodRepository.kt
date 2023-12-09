package com.example.foodservice.repository

import com.example.foodservice.model.entity.FoodEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository: JpaRepository<FoodEntity, Int> {

}