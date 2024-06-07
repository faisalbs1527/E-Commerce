package com.example.ecommerce.screen.orderList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.ecommerce.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment : Fragment(R.layout.fragment_order_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun OrderListPage() {
        TODO("Not yet implemented")
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
}