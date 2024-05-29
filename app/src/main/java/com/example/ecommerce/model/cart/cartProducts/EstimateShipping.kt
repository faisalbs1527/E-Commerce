package com.example.ecommerce.model.cart.cartProducts

data class EstimateShipping(
    val AvailableCountries: List<AvailableCountry>,
    val AvailableStates: List<AvailableState>,
    val City: Any,
    val CountryId: Any,
    val CustomProperties: CustomPropertiesXX,
    val Enabled: Boolean,
    val RequestDelay: Int,
    val StateProvinceId: Any,
    val UseCity: Boolean,
    val ZipPostalCode: String
)