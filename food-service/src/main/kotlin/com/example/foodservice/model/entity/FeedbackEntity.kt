package com.example.foodservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "Feedback")
@IdClass(FeedbackId::class)
class FeedbackEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit var user: UserEntity
    @Id
    @ManyToOne
    @JoinColumn(name = "foodId")
    lateinit var food: FoodEntity
    var rating: Int? = null
    lateinit var comment: String
}