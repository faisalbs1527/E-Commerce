package com.example.ecommerce.network

import com.example.ecommerce.model.checkout.checkoutResponse
import com.example.ecommerce.model.productDetails.ProductDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("product/productdetails/{id}")
    fun getProductById(@Path("id") productID : Int) : Call<ProductDetails>
}