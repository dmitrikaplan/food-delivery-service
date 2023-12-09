package com.example.foodservice.repository

import com.example.foodservice.domain.entity.Feedback
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedbackRepository: JpaRepository<Feedback, Int> {

    fun findAllByFood_FoodId(foodId: Int): List<Feedback>
}