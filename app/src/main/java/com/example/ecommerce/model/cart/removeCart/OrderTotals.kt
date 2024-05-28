package com.example.ecommerce.model.cart.removeCart

data class OrderTotals(
    val CustomProperties: CustomProperties,
    val DisplayTax: Boolean,
    val DisplayTaxRates: Boolean,
    val GiftCards: List<Any>,
    val HideShippingTotal: Boolean,
    val IsEditable: Boolean,
    val OrderTotal: Any,
    val OrderTotalDiscount: Any,
    val PaymentMethodAdditionalFee: Any,
    val RedeemedRewardPoints: Int,
    val RedeemedRewardPointsAmount: Any,
    val RequiresShipping: Boolean,
    val SelectedShippingMethod: Any,
    val Shipping: Any,
    val SubTotal: Any,
    val SubTotalDiscount: Any,
    val Tax: Any,
    val TaxRates: List<Any>,
    val WillEarnRewardPoints: Int
)