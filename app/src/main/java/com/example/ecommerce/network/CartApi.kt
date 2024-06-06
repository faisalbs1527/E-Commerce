package com.example.ecommerce.network

import com.example.ecommerce.model.cart.AddToCartRequest
import com.example.ecommerce.model.cart.AddToCartResponse
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.model.cart.removeCart.RemoveCartRequest
import com.example.ecommerce.model.cart.updateCart.UpdateCartRequest
import com.example.ecommerce.model.checkout.checkoutResponse
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

    @POST("shoppingcart/updatecart")
    suspend fun removeCartProduct(@Body request: RemoveCartRequest) : Response<CartProducts>

    @POST("shoppingcart/updatecart")
    suspend fun updateCartProduct(@Body request: UpdateCartRequest) : Response<CartProducts>

    @GET("confirmorder")
    suspend fun placeOrder(): Response<checkoutResponse>
}