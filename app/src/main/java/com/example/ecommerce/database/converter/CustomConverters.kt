package com.example.ecommerce.database.converter

import androidx.room.TypeConverter
import com.example.ecommerce.model.category.CustomProperties
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CustomConverters {
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