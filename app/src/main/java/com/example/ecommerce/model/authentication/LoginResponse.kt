package com.example.ecommerce.model.authentication

data class LoginResponse(
    val Data: Data,
    val ErrorList: List<Any>,
    val Message: Any
)