package com.example.ecommerce.screen.checkout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.component.BoxCustom
import com.example.ecommerce.component.FinalAmountBox
import com.example.ecommerce.component.PaymentMethod
import com.example.ecommerce.component.TextFieldCustom
import com.example.ecommerce.component.Title
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment(R.layout.fragment_checkout) {

    private val checkoutViewModel: CheckoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkoutViewModel.getOrderTotals()
        initObserver()

    }

    private fun initObserver() {
        checkoutViewModel.orderStatus.observe(this) { status ->
            if (status.orderId.isEmpty()) {
                Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    status.message + "with Order ID: " + status.orderId,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                CheckOutScreen()
            }
        }
    }

    @SuppressLint("ResourceType")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CheckOutScreen() {

        var existingAddress by remember { mutableStateOf("") }
        var billingAddress by remember { mutableStateOf("") }
        var checked by remember { mutableStateOf(false) }
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var company by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var state by remember { mutableStateOf("") }
        var zip by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var faxNumber by remember { mutableStateOf("") }
        val orders by checkoutViewModel.cartResponse.observeAsState()

        if (orders == null) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {

            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.background(brush = gradientColor()),
                        colors = topAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                "One Page Checkout",
                                color = Color.White,
                                fontSize = 18.sp
                            )

                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                findNavController().popBackStack()
                            }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = "nav Icon",
                                    tint = Color.White
                                )
                            }
                        },
                        actions = {
                            BadgedBox(modifier = Modifier.padding(horizontal = 16.dp), badge = {
                                Badge(containerColor = Color.White) {
                                    Text("2")
                                }
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cart),
                                    contentDescription = "Cart Icon",
                                    tint = Color.White,
                                    modifier = Modifier.size(26.dp)
                                )

                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                        shape = RoundedCornerShape(1.dp),
                        elevation = CardDefaults.cardElevation(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {

                        BoxCustom(text = "Billing Address")
                        Title(text = "Address")
//                    TextFieldCustom(
//                        label = "Existing Address :",
//                        value = existingAddress,
//                        isTralingIcon = true,
//                          onValueChange = {existingAddress = it}
//                    )
//                    Row(Modifier.padding(start = 16.dp, top = 8.dp)) {
//                        CustomCheckBox(
//                            isChecked = checked,
//                            radius = 4,
//                            onCheckChange = {checked=it}
//                        )
//                        Text(text = "Ship to the same address",
//                            Modifier.padding(start = 16.dp, top = 2.dp),
//                            fontSize = 14.sp
//                        )
//                    }
//                    Title(text = "Select A Billing Address")
//                    TextFieldCustom(
//                        label = "New",
//                        value = billingAddress,
//                        isTralingIcon = true,
//                        onValueChange = {billingAddress = it}
//                    )
                        TextFieldCustom(
                            label = "First Name :",
                            value = firstName,
                            isTralingIcon = false,
                            onValueChange = { firstName = it }
                        )
                        TextFieldCustom(
                            label = "Last Name :",
                            value = lastName,
                            isTralingIcon = false,
                            onValueChange = { lastName = it }
                        )
                        TextFieldCustom(
                            label = "Email :",
                            value = email,
                            isTralingIcon = false,
                            onValueChange = { email = it }
                        )
                        TextFieldCustom(
                            label = "Company :",
                            value = company,
                            isTralingIcon = false,
                            onValueChange = { company = it }
                        )
                        TextFieldCustom(
                            label = "Country :",
                            value = country,
                            isTralingIcon = false,
                            onValueChange = { country = it }
                        )
                        TextFieldCustom(
                            label = "State / Province :",
                            value = state,
                            isTralingIcon = false,
                            onValueChange = { state = it }
                        )
                        TextFieldCustom(
                            label = "Zip / Postal Code :",
                            value = zip,
                            isTralingIcon = false,
                            onValueChange = { zip = it }
                        )
                        TextFieldCustom(
                            label = "City :",
                            value = city,
                            isTralingIcon = false,
                            onValueChange = { city = it }
                        )
                        TextFieldCustom(
                            label = "Phone Number :",
                            value = phoneNumber,
                            isTralingIcon = false,
                            onValueChange = { phoneNumber = it }
                        )
                        TextFieldCustom(
                            label = "Fax Number :",
                            value = faxNumber,
                            isTralingIcon = false,
                            onValueChange = { faxNumber = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        BoxCustom(text = "Payment Method")
                        PaymentMethod()
                        Spacer(modifier = Modifier.height(8.dp))
                        Title(text = "Order Totals")
                        Spacer(modifier = Modifier.height(16.dp))
                        FinalAmountBox(orders!!) {
                            checkoutViewModel.OrderPlace(
                                firstName, lastName, email, company, country, state,
                                zip, city, phoneNumber, faxNumber
                            )
                        }
                    }

                }
            }
        }
    }

    @Composable
    fun gradientColor(): Brush {
        return Brush.horizontalGradient(
            colors = listOf(
                Color(0xFF0BF7EB),
                Color(0xFF07C5FB),
                Color(0xFF088DF9)
            )
        )
    }

    @Preview
    @Composable
    private fun MessagePreview() {
        CheckOutScreen()
    }

}
