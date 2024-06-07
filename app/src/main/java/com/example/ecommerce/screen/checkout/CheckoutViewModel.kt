package com.example.ecommerce.screen.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.model.cart.cartProducts.OrderTotals
import com.example.ecommerce.model.cart.removeCart.FormValue
import com.example.ecommerce.model.cart.removeCart.RemoveCartRequest
import com.example.ecommerce.model.checkout.checkoutResponse
import com.example.ecommerce.network.CartApi
import com.example.ecommerce.repository.CartRepo
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val repository: CartRepo
) : ViewModel() {

    private val _orderStatus = MutableLiveData<checkoutResponse>()
    val orderStatus: LiveData<checkoutResponse> get() = _orderStatus

    private val _cartResponse : MutableLiveData<OrderTotals> by lazy{
        MutableLiveData<OrderTotals>()
    }
    val cartResponse: LiveData<OrderTotals> get() = _cartResponse

    fun getOrderTotals() = viewModelScope.launch {
        val response = repository.getCartProducts()
        if(response.isSuccessful){
            _cartResponse.value = response.body()?.Data?.OrderTotals
        }
    }

    fun checkOut() = viewModelScope.launch {
        val response = getResponse()
        if (response.isSuccessful) {
            _orderStatus.value = response.body()
            val cartListResponse = repository.getCartProducts()
            if (cartListResponse.isSuccessful) {
                val cartList = cartListResponse.body()?.Data?.Cart?.Items
                if (cartList != null) {
                    RemoveCartItems(cartList)
                }
                Constants.currCartItem=0
            }
        }
        else{
            _orderStatus.value = checkoutResponse(
                message = "Something Went Wrong!!",
                orderId = ""
            )
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.CHECKOUT_URL)
            .build()
    }

    suspend fun getResponse(): Response<checkoutResponse> {
        return getRetrofit().create(CartApi::class.java).placeOrder()
    }

    suspend fun RemoveCartItems(products: List<Item>) {
        for (item in products) {
            repository.removeCartProduct(
                RemoveCartRequest(
                    listOf(
                        FormValue(
                            Key = "removefromcart",
                            Value = "${item.Id}"
                        )
                    )
                )
            )
        }
    }

    fun OrderPlace(
        firstname: String,
        lastName: String,
        email: String,
        company: String,
        country: String,
        state: String,
        zip: String,
        city: String,
        phoneNumber: String,
        faxNumber: String
    ){
        if(!firstname.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !company.isEmpty() && !country.isEmpty() &&
            !state.isEmpty() && !zip.isEmpty() && !city.isEmpty() && !phoneNumber.isEmpty() && !faxNumber.isEmpty()){
            checkOut()
        }
        else{
            _orderStatus.value = checkoutResponse(
                message = "Please Fill out all the Fields!!",
                orderId = ""
            )
        }
    }
}