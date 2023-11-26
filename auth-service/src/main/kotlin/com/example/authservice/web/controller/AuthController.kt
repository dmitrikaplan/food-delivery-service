package com.example.authservice.web.controller

import com.example.authservice.domain.exception.UserAlreadyRegisteredException
import com.example.authservice.service.AuthService
import com.example.authservice.web.mapper.toEntity
import com.example.authservice.web.model.dto.UserAuthDto
import com.example.authservice.web.model.dto.UserDto
import com.example.authservice.web.model.response.JwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping("/register/user")
    fun registerUser(@RequestBody @Validated userDto: UserDto): ResponseEntity<String>{
        return try{
            authService.registerUser(userDto.toEntity())
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyRegisteredException){
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/register/admin")
    fun registerAdmin(@RequestBody @Validated userDto: UserDto): ResponseEntity<String>{
        return try{
            authService.registerAdmin(userDto.toEntity())
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyRegisteredException){
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun authenticate(@RequestBody @Validated userAuthDto: UserAuthDto): ResponseEntity<JwtResponse>{
        return try{
           val jwtResponse = authService.authenticate(userAuthDto.toEntity())
            ResponseEntity.ok().body(jwtResponse)
        } catch (e: UsernameNotFoundException){
            ResponseEntity.badRequest().build()
        }
    }



}