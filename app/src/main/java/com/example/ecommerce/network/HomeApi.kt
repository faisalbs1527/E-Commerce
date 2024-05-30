package com.example.ecommerce.network

import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.slider.SliderItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET(value = "slider/homepageslider")
    suspend fun getSliderImage() : Response <SliderItem>

    @GET(value = "home/homepagecategorieswithproducts")
    suspend fun getCategoryWiseProducts() : Response <CategoryWiseProducts>

    @GET(value = "home/featureproducts")
    suspend fun getProducts() : Response<ProductClass>
}