package com.example.ecommerce.model.category

data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: Any,
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: String,
    val OldPriceValue: Any,
    val PreOrderAvailabilityStartDateTimeUtc: Any,
    val Price: String,
    val PriceValue: Double
)