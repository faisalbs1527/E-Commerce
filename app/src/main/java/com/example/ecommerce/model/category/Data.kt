package com.example.ecommerce.model.category


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.ecommerce.database.dbmodel.CategoryEntity
import com.example.ecommerce.database.dbmodel.toData

@Parcelize
data class Data(
    val CustomProperties: CustomProperties = CustomProperties(),
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory>
) : Parcelable

fun Data.asEntity() = CategoryEntity(
    CustomProperties = this.CustomProperties,
    Id = this.Id,
    Name = this.Name,
    Products = this.Products,
    SeName = this.SeName,
    SubCategories = this.SubCategories
)