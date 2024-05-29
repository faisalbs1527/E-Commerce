package com.example.ecommerce.database.converter

import androidx.room.TypeConverter
import com.example.ecommerce.model.featureProducts.CustomProperties
import com.example.ecommerce.model.featureProducts.PictureModel
import com.example.ecommerce.model.featureProducts.ProductPrice
import com.example.ecommerce.model.featureProducts.ProductSpecificationModel
import com.example.ecommerce.model.featureProducts.ReviewOverviewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromPictureModelList(value: List<PictureModel>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toPictureModel(value: String): List<PictureModel> {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductPrice(value: ProductPrice): String {
        val type = object : TypeToken<ProductPrice>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductPrice(value: String): ProductPrice {
        val type = object : TypeToken<ProductPrice>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductSpecification(value: ProductSpecificationModel): String {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductSpecification(value: String): ProductSpecificationModel {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromReviewOverviewModel(value: ReviewOverviewModel): String {
        val type = object : TypeToken<ReviewOverviewModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toReviewOverviewModel(value: String): ReviewOverviewModel {
        val type = object : TypeToken<ReviewOverviewModel>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromCustomProperties(value: CustomProperties): String {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toCustomProperties(value: String): CustomProperties {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().fromJson(value, type)
    }
}