package com.example.foodservice.repository

import com.example.foodservice.domain.entity.Feedback
import com.example.foodservice.domain.entity.FeedbackId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedbackRepository: JpaRepository<Feedback, FeedbackId> {

    fun findAllByFoodId(foodId: Int): List<Feedback>
}