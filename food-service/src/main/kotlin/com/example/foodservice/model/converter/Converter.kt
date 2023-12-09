package com.example.foodservice.model.converter

interface Converter<T, K> {
    fun toDto(entity: K) : T
    fun toEntity(dto: T) : K
}