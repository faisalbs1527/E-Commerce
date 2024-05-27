package com.example.ecommerce.model.category


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductSpecificationModel(
    val CustomProperties: CustomProperties,
    val Groups:@RawValue List<Any>
) : Parcelable