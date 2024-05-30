package com.example.ecommerce.repository

import android.content.Context
import android.util.Log
import com.example.ecommerce.database.AppDatabase
import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.category.asEntity
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.featureProducts.asEntity
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.model.slider.asEntity
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

    suspend fun getImageSlider():Response<SliderItem> = withContext(Dispatchers.IO){
        val sliders = apiService.getSliderImage()
        sliders.let {
            sliders.body()?.Data?.Sliders?.let { sliderList ->
                dbService.sliderdao().saveImageSliders(sliderList.map { it.asEntity() })
            }
        }
        return@withContext sliders
    }

    suspend fun getCategoryWiseProducts():Response<CategoryWiseProducts> = withContext(Dispatchers.IO){
        val categories = apiService.getCategoryWiseProducts()
        categories.let {
            categories.body()?.Data?.let { categoryList ->
                categoryList.map { it.asEntity() }.let { entity->
                    dbService.categorydao().saveCategory(entity)
                }
            }
        }
        return@withContext categories
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

    //Local Database

    suspend fun getCategoryWiseProductsDb()= withContext(Dispatchers.IO){
        return@withContext dbService.sliderdao().getImageSliders()
    }

    suspend fun getImageSliderDb()= withContext(Dispatchers.IO){
        return@withContext dbService.categorydao().getCategories()
    }

    suspend fun getFeatureProductsDb()= withContext(Dispatchers.IO){
        return@withContext dbService.productdao().getProducts()
    }
}