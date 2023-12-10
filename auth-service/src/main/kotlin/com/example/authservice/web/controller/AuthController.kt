package com.example.authservice.web.controller

import com.example.authservice.domain.exception.RefreshTokenNotFoundException
import com.example.authservice.domain.exception.RefreshTokenExpiredException
import com.example.domain.exception.UserAlreadyRegisteredException
import com.example.domain.exception.UserNotFoundException
import com.example.authservice.service.AuthService
import com.example.authservice.web.mapper.toEntity
import com.example.authservice.web.model.dto.RefreshTokenDto
import com.example.authservice.web.model.dto.UserAuthDto
import com.example.authservice.web.model.dto.UserDto
import com.example.authservice.web.model.response.JwtResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Validated
@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins = ["http://localhost:8080/", "http://localhost:8080/reg", "http://localhost:8080/login"])
class AuthController(
    private val authService: AuthService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/register/user")
    fun registerUser(@RequestBody @Validated userDto: UserDto): ResponseEntity<String>{
        return try{
            authService.registerUser(userDto.toEntity())
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyRegisteredException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/register/admin")
    fun registerAdmin(@RequestBody @Validated userDto: UserDto): ResponseEntity<String>{
        return try{
            authService.registerAdmin(userDto.toEntity())
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyRegisteredException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody @Validated userAuthDto: UserAuthDto): ResponseEntity<JwtResponse>{
        return try{
            log.info("почему не работает на локал хосте ?")
           val jwtResponse = authService.authenticate(userAuthDto.toEntity())
            log.info(jwtResponse.toString())
            ResponseEntity.ok().body(jwtResponse)
        } catch (e: UsernameNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().build()
        }
    }


    @GetMapping("activate/{activationCode}")
    fun activate(@PathVariable activationCode: String): ResponseEntity<String>{
        return try{
            authService.activateAccount(activationCode)
            ResponseEntity.ok().body("Успешная регистрация!")
        } catch (e: UserNotFoundException){
            log.error(e.message)
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/refresh")
    fun refresh(
        principal: Principal,
        @RequestBody refreshTokenDto: RefreshTokenDto
    ): ResponseEntity<JwtResponse>{
        return try{
            val jwtResponse = authService.refresh(refreshTokenDto.refreshToken, principal.name)
            ResponseEntity.ok().body(jwtResponse)
        } catch (e: RefreshTokenExpiredException){
            log.error(e.message)
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } catch (e: RefreshTokenNotFoundException){
            log.error(e.message)
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }
}