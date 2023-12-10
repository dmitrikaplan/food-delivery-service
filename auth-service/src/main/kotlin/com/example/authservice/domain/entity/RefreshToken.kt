package com.example.authservice.domain.entity

import com.example.domain.user.User
import jakarta.persistence.*

@Entity
class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var refreshTokenId: Int? = null

    @Column(length = 1024)
    lateinit var token: String

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    lateinit var user: User
}