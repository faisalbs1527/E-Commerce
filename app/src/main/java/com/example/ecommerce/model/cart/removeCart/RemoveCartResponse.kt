package com.example.ecommerce.model.cart.removeCart

data class RemoveCartResponse(
    val Data: Data,
    val ErrorList: List<String>,
    val Message: Any
)