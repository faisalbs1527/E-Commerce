package com.example.ecommerce.model.category


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class SubCategory(
    val CustomProperties: CustomProperties,
    val Id: Int,
    val Name: String,
    val Products:@RawValue List<Any>,
    val SeName: String,
    val SubCategories:@RawValue List<Any>
) : Parcelable