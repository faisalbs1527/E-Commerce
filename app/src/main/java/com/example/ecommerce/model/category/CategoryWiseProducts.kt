package com.example.ecommerce.model.category


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class CategoryWiseProducts(
    val Data: List<Data>,
    val ErrorList: @RawValue List<Any>,
    val Message:@RawValue Any
) : Parcelable