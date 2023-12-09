package com.example.foodservice.web.controller

import com.example.foodservice.service.FeedbackService
import com.example.foodservice.web.converter.FeedbackConverter
import com.example.foodservice.web.dto.FeedbackDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/feedback")
class FeedbackController(
    private val feedbackService: FeedbackService
) {

    @GetMapping("/{foodId}")
    fun getFeedbackByFoodId(@PathVariable foodId: Int): List<FeedbackDto>{
        val feedback = feedbackService.getFeedback(foodId)
        return FeedbackConverter.toDto(feedback)
    }
}