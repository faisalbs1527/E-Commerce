package com.example.ecommerce.screen.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.cart.AddToCartRequest
import com.example.ecommerce.model.cart.AddToCartResponse
import com.example.ecommerce.model.cart.FormValue
import com.example.ecommerce.model.productDetails.ProductDetails
import com.example.ecommerce.model.slider.SliderItem
import com.example.ecommerce.repository.CartRepo
import com.example.ecommerce.repository.ProductRepo
import com.example.ecommerce.utils.Constants
import kotlinx.coroutines.launch

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

    private val _cartResponse : MutableLiveData<AddToCartResponse> by lazy {
        MutableLiveData<AddToCartResponse>()
    }
    val cartResponse : LiveData<AddToCartResponse> get() = _cartResponse

    private val _showMessage : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage : LiveData<String> get() = _showMessage

    private val cartRepo = CartRepo()

    fun addToCart(productId : Int, quantity : Int = 1) = viewModelScope.launch {
        val request = AddToCartRequest(
            listOf(
                FormValue(
                    Key = "addtocart_$productId.EnteredQuantity",
                    Value = "$quantity"
                ),FormValue(
                    Key = "addtocart_$productId.EnteredGender",
                    Value = "male"
                ))
        )

        val response = cartRepo.AddToCart(productId,request)

        if(response.isSuccessful){
            Constants.currCartItem = response.body()?.Data?.TotalShoppingCartProducts!!
            _cartResponse.value = response.body()
        }
        else{
            _showMessage.value = "Something went wrong!!"
        }
    }
}