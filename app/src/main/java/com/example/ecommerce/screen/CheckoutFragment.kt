package com.example.ecommerce.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.FontsContract.FontFamilyResult
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.w3c.dom.Text

class CheckoutFragment : Fragment(R.layout.fragment_checkout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Message()            }
        }
    }

    @SuppressLint("ResourceType")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Message() {

        var text by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(brush = gradientColor()),
                    colors = topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("One Page Checkout",
                            color = Color.White,
                            fontSize = 18.sp)

                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                                contentDescription = "nav Icon",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        BadgedBox(modifier = Modifier.padding(horizontal = 16.dp),badge = {
                            Badge(containerColor = Color.White){
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
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(1.dp),
                    elevation = CardDefaults.cardElevation(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(brush = gradientColor())
                    )
                    {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "Billing Address",
                            color = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                        text = "Address",
                        fontWeight = FontWeight.Bold
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        value = text,
                        onValueChange = {text = it},
                        label = {Text(
                            "Existing Address:",
                            fontSize = 12.sp,
                            color = Color(0xFF7D828B)
                        )},
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFFCDD1D4),
                            unfocusedIndicatorColor  = Color(0xFFCDD1D4)
                        ),
                        trailingIcon = {
                            Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                        }
                    )
                }

            }
        }
    }

    @Composable
    fun gradientColor() : Brush {
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
        Message()
    }

}

