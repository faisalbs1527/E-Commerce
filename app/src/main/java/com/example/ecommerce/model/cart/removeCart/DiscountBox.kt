package com.example.ecommerce.model.cart.removeCart

data class DiscountBox(
    val AppliedDiscountsWithCodes: List<Any>,
    val CustomProperties: CustomProperties,
    val Display: Boolean,
    val IsApplied: Boolean,
    val Messages: List<Any>
)