package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Feedback
import com.example.foodservice.web.dto.FeedbackDto

object FeedbackConverter: Converter<FeedbackDto, Feedback> {
    override fun toDto(entity: Feedback): FeedbackDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: FeedbackDto): Feedback {
        TODO("Not yet implemented")
    }

}