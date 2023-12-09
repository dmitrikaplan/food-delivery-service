package com.example.domain.repository

import com.example.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {

    fun findUserByUsername(username: String): User?
    fun findUserByActivationCode(activationCode: String?): User?

    fun findUserById(userId: Int): User?
}