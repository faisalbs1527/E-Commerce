package com.example.ecommerce.repository

import android.util.Log
import com.example.ecommerce.model.productDetails.ProductDetails
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.ProductApi
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class ProductRepo {
    val apiService = ApiClient.getRetrofit().create(ProductApi::class.java)

    fun getProductDetails(productID: Int,callback: (ProductDetails?,Throwable?)->Unit){
        apiService.getProductById(productID).enqueue(object : retrofit2.Callback<ProductDetails?> {
            override fun onResponse(p0: Call<ProductDetails?>, p1: Response<ProductDetails?>) {
                val responseBody = p1.body()!!
                callback(responseBody,null)
            }

            override fun onFailure(p0: Call<ProductDetails?>, p1: Throwable) {
                callback(null,p1)
                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }
}