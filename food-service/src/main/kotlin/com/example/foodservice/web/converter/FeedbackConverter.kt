package com.example.foodservice.web.converter

import com.example.foodservice.domain.entity.Feedback
import com.example.foodservice.web.dto.FeedbackDto

object FeedbackConverter: Converter<FeedbackDto, Feedback> {
    override fun toDto(entity: Feedback): FeedbackDto =
        FeedbackDto(
            username = entity.user.username,
            foodId = entity.food.foodId!!,
            rating = entity.rating!!,
            comment = entity.comment
        )

    override fun toEntity(dto: FeedbackDto): Feedback {
        TODO("Not yet implemented")
    }


    fun toDto(entities: List<Feedback>): List<FeedbackDto>{
        return entities.map {
            toDto(it)
        }
    }

}