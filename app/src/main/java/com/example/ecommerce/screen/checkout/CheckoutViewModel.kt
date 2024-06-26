package com.example.ecommerce.screen.checkout

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.database.AppDatabase
import com.example.ecommerce.database.dbmodel.OrderEntity
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.model.cart.cartProducts.OrderTotals
import com.example.ecommerce.model.cart.removeCart.FormValue
import com.example.ecommerce.model.cart.removeCart.RemoveCartRequest
import com.example.ecommerce.model.checkout.checkoutResponse
import com.example.ecommerce.network.CartApi
import com.example.ecommerce.repository.CartRepo
import com.example.ecommerce.utils.ConnectivityUtil
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val repository: CartRepo,
    private val dbService: AppDatabase,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _orderStatus = MutableLiveData<checkoutResponse>()
    val orderStatus: LiveData<checkoutResponse> get() = _orderStatus

    private val _cartResponse: MutableLiveData<OrderTotals> by lazy {
        MutableLiveData<OrderTotals>()
    }
    val cartResponse: LiveData<OrderTotals> get() = _cartResponse

    private val _loader: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val loader: LiveData<Boolean> get() = _loader

    private var OrderId: String? = null
    private var totalAmount: String? = null
    private var email: String? = sharedPreferences.getString("email", null)

    fun getOrderTotals() = viewModelScope.launch {
        if (ConnectivityUtil.isNetworkAvailable(context.applicationContext)) {
            val response = repository.getCartProducts()
            if (response.isSuccessful) {
                _cartResponse.value = response.body()?.Data?.OrderTotals
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkOut(totalAmount: String, points: String) = viewModelScope.launch {
        if (ConnectivityUtil.isNetworkAvailable(context.applicationContext)) {
            val response = getResponse()
            if (response.isSuccessful) {
                _orderStatus.value = response.body()
                OrderId = response.body()?.orderId
                val cartListResponse = repository.getCartProducts()
                if (cartListResponse.isSuccessful) {
                    val cartList = cartListResponse.body()?.Data?.Cart?.Items
                    if (cartList != null) {
                        RemoveCartItems(cartList)
                        saveToDatabase(
                            OrderEntity(
                                email = email!!,
                                userToken = Constants.TOKEN!!,
                                totalAmount = totalAmount,
                                points = points,
                                orderId = OrderId!!,
                                products = cartList,
                                date = getCurrentFormattedDate()
                            )
                        )
                    }
                    _loader.value = false
                    Constants.currCartItem = 0
                }
            } else {
                _orderStatus.value = checkoutResponse(
                    message = "Something Went Wrong!!",
                    orderId = ""
                )
            }
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

    @RequiresApi(Build.VERSION_CODES.O)
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
        faxNumber: String,
        totalAmount: String,
        earnedPoints: String
    ) {
        if (!firstname.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !company.isEmpty() && !country.isEmpty() &&
            !state.isEmpty() && !zip.isEmpty() && !city.isEmpty() && !phoneNumber.isEmpty() && !faxNumber.isEmpty()
        ) {
            _loader.value = true
            checkOut(totalAmount,earnedPoints)
        } else {
            _orderStatus.value = checkoutResponse(
                message = "Please Fill out all the Fields!!",
                orderId = ""
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentFormattedDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }

    suspend fun saveToDatabase(orderInfo: OrderEntity) {
        dbService.orderdao().saveOrderInfo(orderInfo)
    }
}