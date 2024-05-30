package com.example.ecommerce.screen.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.database.dbmodel.toData
import com.example.ecommerce.database.dbmodel.toProductData
import com.example.ecommerce.database.dbmodel.toSlider
import com.example.ecommerce.model.category.CategoryWiseProducts
import com.example.ecommerce.model.featureProducts.ProductClass
import com.example.ecommerce.model.slider.Data
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.repository.HomeRepo
import com.example.ecommerce.utils.ConnectivityUtil
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private var _sliderImages = MutableLiveData<SliderItem>()
    val sliderImages : LiveData<SliderItem> get() = _sliderImages

    private var _categoryProducts = MutableLiveData<CategoryWiseProducts>()
    val categoryProducts : LiveData<CategoryWiseProducts> get() = _categoryProducts

    private var _products = MutableLiveData<ProductClass>()
    val products : LiveData<ProductClass> get() = _products

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    private val repository = HomeRepo(context)

    fun fetchSliderImages(context: Context) = viewModelScope.launch{
        if(ConnectivityUtil.isNetworkAvailable(context.applicationContext)){
            val response = repository.getImageSlider()

            if(response.isSuccessful){
                _sliderImages.value = response.body()
            }
        }
        else{
            val response = repository.getImageSliderDb()
            _sliderImages.value = SliderItem(
                Data = Data(
                    IsEnabled = true,
                    Sliders = response.map {
                        it.toSlider()
                    }
                ),
                ErrorList = emptyList(),
                Message = " "
            )
//            _sliderImages.value?.Data?.Sliders = response.map {
//                it.toSlider()
//            }
        }
    }

    fun fetchCategoryWiseProducts(context: Context) = viewModelScope.launch{
        if(ConnectivityUtil.isNetworkAvailable(context.applicationContext)){
            val response = repository.getCategoryWiseProducts()

            if(response.isSuccessful){
                _categoryProducts.value = response.body()
            }
        }
        else{
            val response = repository.getCategoryWiseProductsDb()
            _categoryProducts.value = CategoryWiseProducts(
                Data = response.map {
                    it.toData()
                },
                ErrorList = emptyList(),
                Message = " "
            )
//            _categoryProducts.value?.Data = response.map {
//                it.toData()
//            }
        }
    }

    fun fetchFeaturedProducts(context: Context) = viewModelScope.launch{
        if(ConnectivityUtil.isNetworkAvailable(context.applicationContext)){
            val response = repository.getFeatureProducts()

            if(response.isSuccessful){
                _products.value = response.body()
            }
            println(repository.getFeatureProductsDb())
        }
        else{
            val response = repository.getFeatureProductsDb()
            _products.value = ProductClass(
                Data = response.map {
                    it.toProductData()
                },
                ErrorList = emptyList(),
                Message = " "
            )
//            _products.value?.Data = response.map {
//                it.toProductData()
//            }
        }
    }

}