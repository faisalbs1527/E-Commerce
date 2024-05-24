package com.example.ecommerce.screen.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerce.model.productDetails.ProductDetails
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.repository.ProductRepo

class ProductViewModel : ViewModel(){
    private val _productdetails = MutableLiveData<ProductDetails>()
    val productDetails : LiveData<ProductDetails> get() = _productdetails

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    private val repository = ProductRepo()

    fun fetchProductDeatils(productID : Int){
        repository.getProductDetails(productID){ productdetails , throwable->
            if(productdetails != null){
                _productdetails.postValue(productdetails!!)
            }
            else{
                _error.postValue(throwable?.message)
            }
        }
    }
}