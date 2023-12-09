package com.example.foodservice.model.entity

import com.example.foodservice.model.dto.OrderState
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "Category")
class CategoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var categoryId: Int? = null
    lateinit var categoryName : String
    @ManyToMany
    @JoinTable(name = "Food_Category",
        joinColumns = [JoinColumn(name = "categoryId")],
        inverseJoinColumns = [JoinColumn(name = "foodId")])
    var food: List<FoodEntity> = mutableListOf()
}