package com.example.foodservice.repository

import com.example.foodservice.domain.entity.Food
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository: JpaRepository<Food, Int> {

    fun findAllByFoodNameContaining(pageable: Pageable, foodName: String): List<Food>
}