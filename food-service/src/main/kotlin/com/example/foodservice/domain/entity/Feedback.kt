package com.example.foodservice.domain.entity

import com.example.domain.user.User
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
class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var feedbackId: Int? = null
    @ManyToOne
    @JoinColumn(name = "id")
    lateinit var user: User

    @ManyToOne
    @JoinColumn(name = "foodId")
    lateinit var food: Food

    var rating: Int? = null
    lateinit var comment: String
}