package com.example.authservice.web.model.response

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String
)