package com.example.ecommerce.model.cart

data class AddToCartResponse(
    val Data: Data,
    val ErrorList: List<Any>,
    val Message: String
)