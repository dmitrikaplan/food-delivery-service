package com.example.foodservice.repository

import com.example.foodservice.model.entity.FeedbackEntity
import com.example.foodservice.model.entity.FeedbackId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedbackRepository: JpaRepository<FeedbackEntity, FeedbackId> {

}