package com.example.ecommerce.network

import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.slider.SliderItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET(value = "slider/homepageslider")
    fun getSliderImage() : Call <SliderItem>

    @GET(value = "home/homepagecategorieswithproducts")
    fun getCategoryWiseProducts() : Call <CategoryWiseProducts>

    @GET(value = "home/featureproducts")
    suspend fun getProducts() : Response<ProductClass>
}