package com.example.authservice.service

import com.example.authservice.domain.user.UserEntity
import com.example.authservice.web.model.response.JwtResponse
import org.springframework.stereotype.Service

@Service
interface AuthService {

    fun registerUser(user: UserEntity)

    fun registerAdmin(user: UserEntity)

    fun authenticate(user: UserEntity): JwtResponse

    fun activateAccount(code: String)

    fun passwordRecovery(user: UserEntity)


}