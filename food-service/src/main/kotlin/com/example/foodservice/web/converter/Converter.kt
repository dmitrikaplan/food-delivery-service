package com.example.foodservice.web.converter

interface Converter<T, K> {
    fun toDto(entity: K) : T
    fun toEntity(dto: T) : K
}