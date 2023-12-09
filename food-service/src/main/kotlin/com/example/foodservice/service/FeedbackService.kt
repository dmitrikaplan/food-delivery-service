package com.example.foodservice.service

import com.example.foodservice.domain.entity.Feedback
import org.springframework.stereotype.Service

@Service
interface FeedbackService {


    fun getFeedback(foodId: Int): List<Feedback>


}