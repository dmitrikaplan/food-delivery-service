package com.example.foodservice.web.controller

import com.example.foodservice.service.FeedbackService
import com.example.foodservice.web.converter.FeedbackConverter
import com.example.foodservice.web.dto.FeedbackDto
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/feedback")
@CrossOrigin(origins = ["http://localhost:8080/"])
class FeedbackController(
    private val feedbackService: FeedbackService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{foodId}")
    fun getFeedbackByFoodId(@PathVariable foodId: Int): List<FeedbackDto>{
        val feedback = feedbackService.getFeedback(foodId)
        return FeedbackConverter.toDto(feedback)
    }

    @PostMapping
    fun createFeedback(@RequestBody feedback: FeedbackDto) : FeedbackDto{
        val createdFeedback = feedbackService.saveFeedback(feedback.username, feedback.foodId, feedback.rating, feedback.comment)
        return FeedbackConverter.toDto(createdFeedback)
    }
}