package com.example.ecommerce.model.cart.removeCart

data class EstimateShipping(
    val AvailableCountries: List<Any>,
    val AvailableStates: List<Any>,
    val City: Any,
    val CountryId: Any,
    val CustomProperties: CustomProperties,
    val Enabled: Boolean,
    val RequestDelay: Int,
    val StateProvinceId: Any,
    val UseCity: Boolean,
    val ZipPostalCode: Any
)