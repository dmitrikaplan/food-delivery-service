package com.example.foodservice.model.entity

import java.io.Serializable

class FeedbackId : Serializable{
    var userId: Int? = null
    var foodId: Int? = null

    constructor(userId: Int, foodId: Int){
        this.userId = userId
        this.foodId = foodId
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FeedbackId

        if (userId != other.userId) return false
        if (foodId != other.foodId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId ?: 0
        result = 31 * result + (foodId ?: 0)
        return result
    }


}