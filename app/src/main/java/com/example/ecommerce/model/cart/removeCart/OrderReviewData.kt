package com.example.ecommerce.model.cart.removeCart

data class OrderReviewData(
    val BillingAddress: BillingAddress,
    val CustomProperties: CustomProperties,
    val CustomValues: CustomValues,
    val Display: Boolean,
    val IsShippable: Boolean,
    val PaymentMethod: Any,
    val PickupAddress: PickupAddress,
    val SelectedPickupInStore: Boolean,
    val ShippingAddress: ShippingAddress,
    val ShippingMethod: Any
)