package com.example.foodservice.domain.entity

import com.example.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "Feedback")
@IdClass(FeedbackId::class)
class Feedback {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    lateinit var user: User

    @Id
    @ManyToOne
    @JoinColumn(name = "foodId")
    lateinit var food: Food

    var rating: Int? = null
    lateinit var comment: String
}