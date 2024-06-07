package com.example.ecommerce.database.converter

import androidx.room.TypeConverter
import com.example.ecommerce.model.cart.cartProducts.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderConverter {
    @TypeConverter
    fun fromItemList(itemList: List<Item>): String {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().toJson(itemList, type)
    }
    @TypeConverter
    fun toItemList(itemListString: String): List<Item> {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(itemListString, type)
    }
}