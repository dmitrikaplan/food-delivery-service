package com.example.authservice.repository

import com.example.authservice.domain.entity.RefreshToken
import com.example.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: JpaRepository<RefreshToken, Int> {

    fun deleteByToken(token: String)
    fun existsByToken(token: String): Boolean

    fun findRefreshTokenByUser(user: User): RefreshToken?
}