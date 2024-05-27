package com.example.ecommerce.model.category


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Data(
    val CustomProperties: CustomProperties,
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory>
) : Parcelable