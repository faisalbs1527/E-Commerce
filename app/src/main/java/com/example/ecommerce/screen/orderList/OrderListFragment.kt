package com.example.ecommerce.screen.orderList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.component.TextFieldCustom
import com.example.ecommerce.database.dbmodel.OrderEntity
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment : Fragment(R.layout.fragment_order_list) {

    private val orderlistviewmodel: OrderListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderlistviewmodel.getOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                OrderListPage()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun OrderListPage() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(
                        brush = gradientColor()
                    ),
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
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = "Placed Order List",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    },
                    actions = {
                        BadgedBox(modifier = Modifier.padding(horizontal = 16.dp), badge = {
                            Badge(containerColor = Color.White) {
                                Text(Constants.currCartItem.toString())
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
        ) {
            BodyContent(it)
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

    @Composable
    fun BodyContent(it: PaddingValues) {
        val orderlist by orderlistviewmodel.orders.observeAsState()
        if (orderlist.isNullOrEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
               items(orderlist!!) { order ->
                    OrderCard(order = order)
                }
            }
        }
    }

    @Composable
    fun OrderCard(order: OrderEntity) {
        OutlinedCard(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(280.dp)
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = "Order Details", modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(
                            id = R.color.order_tag
                        )
                    )
                    .padding(start = 12.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                fontSize = 18.sp
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "OrderID: ", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = order.orderId,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Order Status: ", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "In Progress",
                    color = colorResource(id = R.color.green_light),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Order Date: ", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = order.date,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Order Total: ", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = order.totalAmount,
                    color = colorResource(id = R.color.green_light),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Total Items: ", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = order.products.size.toString(),
                    color = colorResource(id = R.color.green_light),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    @Preview
    @Composable
    private fun OrderListPreview() {
        Surface {
            OrderCard(OrderEntity(email = "", orderId = "hsdgash892u3ywqwhnsjansuqqywe82", userToken = "", products = emptyList(), points = "", date = "11-2-2024", totalAmount = "$120.80"))
        }
    }
}