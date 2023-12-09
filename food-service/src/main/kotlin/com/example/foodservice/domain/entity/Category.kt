package com.example.foodservice.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "Category")
class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var categoryId: Int? = null
    lateinit var categoryName : String

    @ManyToMany
    @JoinTable(name = "Food_Category",
        joinColumns = [JoinColumn(name = "categoryId")],
        inverseJoinColumns = [JoinColumn(name = "foodId")])
    var food: List<Food> = mutableListOf()
}