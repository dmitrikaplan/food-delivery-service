package com.example.authservice.service

import com.example.authservice.domain.user.User
import com.example.authservice.web.model.response.JwtResponse
import org.springframework.stereotype.Service

@Service
interface AuthService {

    fun registerUser(user: User)

    fun registerAdmin(user: User)

    fun authenticate(user: User): JwtResponse

    fun activateAccount(code: String)

    fun passwordRecovery(user: User)


}