package com.example.ecommerce.repository

import android.util.Log
import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.HomeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class HomeRepo {
    val apiService = ApiClient.getRetrofit().create(HomeApi::class.java)

    fun getImageSlider(callback: (SliderItem?,Throwable?)->Unit){
        apiService.getSliderImage().enqueue(object : Callback<SliderItem?> {
            override fun onResponse(p0: Call<SliderItem?>, p1: Response<SliderItem?>) {
                val responseBody = p1.body()!!
                callback(responseBody,null)
            }

            override fun onFailure(p0: Call<SliderItem?>, p1: Throwable) {
                callback(null,p1)
                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }

    fun getCategoryWiseProducts(callback: (CategoryWiseProducts?, Throwable?) -> Unit){
        apiService.getCategoryWiseProducts().enqueue(object : Callback<CategoryWiseProducts?> {
            override fun onResponse(
                p0: Call<CategoryWiseProducts?>,
                p1: Response<CategoryWiseProducts?>
            ) {
                val responseBody = p1.body()!!
                callback(responseBody,null)
            }

            override fun onFailure(p0: Call<CategoryWiseProducts?>, p1: Throwable) {
                callback(null,p1)
                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }

    fun getFeatureProducts(callback: (ProductClass?,Throwable?) -> Unit){

        apiService.getProducts().enqueue(object : retrofit2.Callback<ProductClass?> {
            override fun onResponse(p0: Call<ProductClass?>, p1: Response<ProductClass?>) {
                val responseBody = p1.body()!!
                callback(responseBody,null)

            }

            override fun onFailure(p0: Call<ProductClass?>, p1: Throwable) {
                callback(null,p1)
                Log.d("MainActivity","OnFailure: "+p1.message)
            }
        })
    }
}