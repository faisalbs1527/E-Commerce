package com.example.ecommerce.model.cart.removeCart

data class Data(
    val AnonymousPermissed: Boolean,
    val Cart: Cart,
    val EstimateShipping: EstimateShipping,
    val OrderTotals: OrderTotals,
    val SelectedCheckoutAttributes: String
)