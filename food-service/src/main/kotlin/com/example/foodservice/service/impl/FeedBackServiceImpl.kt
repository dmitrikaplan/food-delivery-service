package com.example.foodservice.service.impl

import com.example.foodservice.domain.entity.Feedback
import com.example.foodservice.repository.FeedbackRepository
import com.example.foodservice.service.FeedbackService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FeedBackServiceImpl(
    private val feedbackRepository: FeedbackRepository
): FeedbackService {

    @Transactional
    override fun getFeedback(foodId: Int): List<Feedback> {
        return feedbackRepository.findAllByFoodId(foodId)
    }
}