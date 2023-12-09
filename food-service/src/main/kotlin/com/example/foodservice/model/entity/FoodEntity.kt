package com.example.foodservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "Food")
class FoodEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var foodId: Int? = null
    lateinit var foodName : String
    lateinit var description : String
    var price: Int? = null
    lateinit var imageUrl : String
    @ManyToMany(mappedBy = "food")
    var categories: List<CategoryEntity> = mutableListOf()
}