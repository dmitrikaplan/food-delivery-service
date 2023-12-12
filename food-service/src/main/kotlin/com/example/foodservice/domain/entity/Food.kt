package com.example.foodservice.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "Food")
class Food{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var foodId: Int? = null

    lateinit var foodName : String
    lateinit var description : String
    var price: Int? = null
    lateinit var base64Image : String

    @ManyToMany(mappedBy = "food")
    var categories: List<Category> = mutableListOf()
}