package com.example.authservice.repository

import com.example.authservice.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findUserByUsername(username: String): User?
    fun findUserByActivationCode(activationCode: String?): User?
}