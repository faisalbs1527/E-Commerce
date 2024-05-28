package com.example.ecommerce.repository

import com.example.ecommerce.model.cart.AddToCartRequest
import com.example.ecommerce.model.cart.AddToCartResponse
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.CartApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CartRepo {

    val apiService = ApiClient.getRetrofit().create(CartApi::class.java)

    suspend fun AddToCart(productID : Int, request: AddToCartRequest): Response<AddToCartResponse> = withContext(Dispatchers.IO){
        return@withContext apiService.addProductToCart(productID,request)
    }

    suspend fun getCartProducts(): Response<CartProducts> = withContext(Dispatchers.IO){
        return@withContext apiService.getCartProducts()
    }
}