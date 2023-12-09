package com.example.authservice.web.controller

import com.example.domain.exception.UserAlreadyRegisteredException
import com.example.domain.exception.UserNotFoundException
import com.example.authservice.service.AuthService
import com.example.authservice.web.mapper.toEntity
import com.example.authservice.web.model.dto.UserAuthDto
import com.example.authservice.web.model.dto.UserDto
import com.example.authservice.web.model.response.JwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/v1/auth/")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping("register/user")
    fun registerUser(@RequestBody @Validated userDto: UserDto): ResponseEntity<String>{
        return try{
            authService.registerUser(userDto.toEntity())
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyRegisteredException){
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("register/admin")
    fun registerAdmin(@RequestBody @Validated userDto: UserDto): ResponseEntity<String>{
        return try{
            authService.registerAdmin(userDto.toEntity())
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyRegisteredException){
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("authenticate/")
    fun authenticate(@RequestBody @Validated userAuthDto: UserAuthDto): ResponseEntity<JwtResponse>{
        return try{
           val jwtResponse = authService.authenticate(userAuthDto.toEntity())
            ResponseEntity.ok().body(jwtResponse)
        } catch (e: UsernameNotFoundException){
            ResponseEntity.badRequest().build()
        }
    }


    @GetMapping("activate/{activationCode}")
    fun activate(@PathVariable activationCode: String): ResponseEntity<String>{
        return try{
            authService.activateAccount(activationCode)
            ResponseEntity.ok().body("Успешная регистрация!")
        } catch (e: UserNotFoundException){
            ResponseEntity.badRequest().body(e.message)
        }
    }
}