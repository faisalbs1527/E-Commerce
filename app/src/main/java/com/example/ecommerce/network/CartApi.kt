package com.example.ecommerce.network

import com.example.ecommerce.model.cart.AddToCartRequest
import com.example.ecommerce.model.cart.AddToCartResponse
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {

    @POST("shoppingCart/AddProductToCart/details/{id}/1")
    suspend fun addProductToCart(@Path("id") productId : Int, @Body request : AddToCartRequest) : Response<AddToCartResponse>

    @GET("shoppingcart/cart")
    suspend fun getCartProducts() : Response<CartProducts>
}