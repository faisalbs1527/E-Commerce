package com.example.ecommerce.model.productDetails

import com.example.ecommerce.model.productDetails.AvailableCountry
import com.example.ecommerce.model.productDetails.AvailableState
import com.example.ecommerce.model.productDetails.CustomPropertiesXXX

data class ProductEstimateShipping(
    val AvailableCountries: List<AvailableCountry>,
    val AvailableStates: List<AvailableState>,
    val City: Any,
    val CountryId: Any,
    val CustomProperties: CustomPropertiesXXX,
    val Enabled: Boolean,
    val ProductId: Int,
    val RequestDelay: Int,
    val StateProvinceId: Any,
    val UseCity: Boolean,
    val ZipPostalCode: Any
)