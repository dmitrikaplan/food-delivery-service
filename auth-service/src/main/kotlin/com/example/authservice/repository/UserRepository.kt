package com.example.authservice.repository

import com.example.authservice.domain.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {

    fun findUserByUsername(username: String): UserEntity?
    fun findUserByActivationCode(activationCode: String?): UserEntity?
}