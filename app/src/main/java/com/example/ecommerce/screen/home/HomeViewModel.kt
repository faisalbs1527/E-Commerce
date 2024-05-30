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

    fun fetchSliderImages(){
        repository.getImageSlider { sliderItem, throwable ->
            if(sliderItem != null){
                _sliderImages.postValue(sliderItem!!)
            }
            else{
                _error.postValue(throwable?.message)
            }
        }
    }

    fun fetchCategoryWiseProducts(){
        repository.getCategoryWiseProducts { categoryWiseProducts, throwable ->
            if(categoryWiseProducts != null){
                _categoryProducts.postValue(categoryWiseProducts!!)
            }
            else{
                _error.postValue(throwable?.message)
            }
        }
    }

    fun fetchFeaturedProducts() = viewModelScope.launch{
        val response = repository.getFeatureProducts()

        if(response.isSuccessful){
            _products.value = response.body()
        }
    }

}