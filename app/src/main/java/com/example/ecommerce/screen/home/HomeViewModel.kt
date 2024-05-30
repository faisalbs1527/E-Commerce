package com.example.ecommerce.screen.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.repository.HomeRepo
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private val _sliderImages = MutableLiveData<SliderItem>()
    val sliderImages : LiveData<SliderItem> get() = _sliderImages

    private val _categoryProducts = MutableLiveData<CategoryWiseProducts>()
    val categoryProducts : LiveData<CategoryWiseProducts> get() = _categoryProducts

    private val _products = MutableLiveData<ProductClass>()
    val products : LiveData<ProductClass> get() = _products

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    private val repository = HomeRepo(context)

    fun fetchSliderImages() = viewModelScope.launch{
        val response = repository.getImageSlider()

        if(response.isSuccessful){
            _sliderImages.value = response.body()
        }
    }

    fun fetchCategoryWiseProducts() = viewModelScope.launch{
        val response = repository.getCategoryWiseProducts()

        if(response.isSuccessful){
            _categoryProducts.value = response.body()
        }
    }

    fun fetchFeaturedProducts() = viewModelScope.launch{
        val response = repository.getFeatureProducts()

        if(response.isSuccessful){
            _products.value = response.body()
        }
    }

}