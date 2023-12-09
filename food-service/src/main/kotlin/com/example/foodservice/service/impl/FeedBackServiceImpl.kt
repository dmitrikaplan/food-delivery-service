package com.example.foodservice.service.impl

import com.example.domain.exception.UserNotFoundException
import com.example.domain.repository.UserRepository
import com.example.foodservice.domain.entity.Feedback
import com.example.foodservice.domain.exception.food.FoodNotFoundException
import com.example.foodservice.repository.AddressRepository
import com.example.foodservice.repository.CartDetailRepository
import com.example.foodservice.repository.CartRepository
import com.example.foodservice.repository.FeedbackRepository
import com.example.foodservice.repository.FoodRepository
import com.example.foodservice.repository.OrderRepository
import com.example.foodservice.repository.PaymentRepository
import com.example.foodservice.service.FeedbackService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FeedBackServiceImpl(
    private val userRepository: UserRepository,
    private val foodRepository: FoodRepository,
    private val feedbackRepository: FeedbackRepository
): FeedbackService {

    @Transactional
    override fun getFeedback(foodId: Int): List<Feedback> = feedbackRepository.findAllByFood_FoodId(foodId)

    @Transactional
    override fun saveFeedback(username: String, foodId: Int, rating: Int, comment: String) = feedbackRepository.save(
        Feedback().apply
            {
                this.user = userRepository.findUserByUsername(username) ?: throw UserNotFoundException()
                this.food = foodRepository.findFoodByFoodId(foodId) ?: throw FoodNotFoundException()
                this.rating = rating
                this.comment = comment
            }
    )
}