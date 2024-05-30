package com.example.ecommerce.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.model.category.CustomProperties
import com.example.ecommerce.model.category.Data
import com.example.ecommerce.model.category.Product
import com.example.ecommerce.model.category.SubCategory

@Entity(tableName = "category")
data class CategoryEntity(
    val CustomProperties: CustomProperties = CustomProperties(),
    @PrimaryKey
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory>
)

fun CategoryEntity.toData() = Data(
    CustomProperties = this.CustomProperties,
    Id = this.Id,
    Name = this.Name,
    Products = this.Products,
    SeName = this.SeName,
    SubCategories = this.SubCategories
)
