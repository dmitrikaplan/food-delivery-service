package com.example.authservice.service.impl

import com.example.authservice.domain.exception.UserAlreadyRegisteredException
import com.example.authservice.domain.exception.UserNotFoundException
import com.example.domain.user.User
import com.example.domain.repository.UserRepository
import com.example.authservice.service.AuthService
import com.example.authservice.service.MailService
import com.example.authservice.web.model.response.JwtResponse
import com.example.domain.jwt.JwtService
import com.example.domain.user.Role
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val mailService: MailService,
    private val jwtService: JwtService
): AuthService {
    override fun registerUser(user: User) {
        userRepository.findUserByUsername(user.username)?.let {
            throw UserAlreadyRegisteredException()
        }

        user.password = passwordEncoder.encode(user.password)
        user.role = Role.ROLE_USER
        user.activationCode = UUID.randomUUID().toString().replace("-", "")

        mailService.activateUserByEmail(user.email, user.username, user.activationCode!!)

        userRepository.save(user)
    }

    override fun registerAdmin(user: User) {
        userRepository.findUserByUsername(user.username)?.let {
            throw UserAlreadyRegisteredException()
        }

        user.password = passwordEncoder.encode(user.password)
        user.role = Role.ROLE_ADMIN
        user.activationCode = UUID.randomUUID().toString().replace("-", "")

        mailService.activateUserByEmail(user.email, user.username, user.activationCode!!)

        // TODO: Добавить дополнительные этап по разрешению стать админом
        userRepository.save(user)
    }

    override fun authenticate(user: User): JwtResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                user.username,
                user.password
            )
        )

        val userDetails = userRepository.findUserByUsername(user.username)!!

        val accessToken = jwtService.generateAccessToken(userDetails)
        val refreshToken = jwtService.generateRefreshToken(userDetails)

        return JwtResponse(accessToken, refreshToken)
    }

    override fun activateAccount(code: String) {
        val user = userRepository.findUserByActivationCode(code) ?: throw UserNotFoundException()

        user.let {
            it.activated = true
            it.activationCode = null
        }

        userRepository.save(user)
    }

    override fun passwordRecovery(user: User) {
        TODO("Not yet implemented")
    }
}