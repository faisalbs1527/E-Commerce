package com.example.ecommerce.repository

import android.content.Context
import android.util.Log
import com.example.ecommerce.database.AppDatabase
import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.featureProducts.asEntity
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.network.ApiClient
import com.example.ecommerce.network.HomeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class HomeRepo(private val context: Context) {
    val apiService = ApiClient.getRetrofit().create(HomeApi::class.java)
    val dbService = AppDatabase.invoke(context)

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
    suspend fun getFeatureProducts():Response<ProductClass> = withContext(Dispatchers.IO){
        val products = apiService.getProducts()
        products.let {
            products.body()?.Data?.let { datalist ->
                dbService.productdao().saveProducts(datalist.map { it.asEntity() })
            }
        }
        return@withContext products
    }
}