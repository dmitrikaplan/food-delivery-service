package com.example.foodservice.model.converter

import com.example.foodservice.model.dto.Feedback
import com.example.foodservice.model.entity.FeedbackEntity

object FeedbackConverter: Converter<Feedback, FeedbackEntity> {
    override fun toDto(entity: FeedbackEntity): Feedback {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: Feedback): FeedbackEntity {
        TODO("Not yet implemented")
    }

}