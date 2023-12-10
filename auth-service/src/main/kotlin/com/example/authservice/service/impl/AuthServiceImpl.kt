package com.example.authservice.service.impl

import com.example.authservice.domain.exception.RefreshTokenNotFoundException
import com.example.authservice.domain.entity.RefreshToken
import com.example.authservice.domain.exception.RefreshTokenExpiredException
import com.example.authservice.repository.RefreshTokenRepository
import com.example.domain.exception.UserAlreadyRegisteredException
import com.example.domain.exception.UserNotFoundException
import com.example.domain.user.User
import com.example.domain.repository.UserRepository
import com.example.authservice.service.AuthService
import com.example.authservice.service.MailService
import com.example.authservice.web.model.response.JwtResponse
import com.example.domain.jwt.JwtService
import com.example.domain.user.Role
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val mailService: MailService,
    private val jwtService: JwtService,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userDetailsService: UserDetailsService
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
        val refreshToken = getRefreshToken(userDetails)
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

    override fun refresh(token: String, username: String): JwtResponse {

        if(!jwtService.isRefreshTokenValid(token)){
            refreshTokenRepository.deleteByToken(token)
            throw RefreshTokenExpiredException()
        }

        if(!refreshTokenRepository.existsByToken(token))
            throw RefreshTokenNotFoundException()

        val userDetails = userDetailsService.loadUserByUsername(username)

        val accessToken = jwtService.generateAccessToken(userDetails)

        return JwtResponse(accessToken, token)
    }


    private fun getRefreshToken(user: User): String{

        val refreshToken = refreshTokenRepository.findRefreshTokenByUser(user)
            ?: return saveRefreshToken(user)

        if(!jwtService.isRefreshTokenValid(refreshToken.token))
            return updateRefreshToken(refreshToken, user)

        return refreshToken.token
    }

    private fun updateRefreshToken(refreshToken: RefreshToken, userDetails: UserDetails): String {
        val token = jwtService.generateRefreshToken(userDetails)
        refreshToken.token = token
        refreshTokenRepository.save(refreshToken)
        return token
    }

    private fun saveRefreshToken(user: User): String{
        val token =  jwtService.generateRefreshToken(user)
        val refreshToken = RefreshToken().apply {
            this.token = token
            this.user = user
        }
        refreshTokenRepository.save(refreshToken)
        return token
    }
}