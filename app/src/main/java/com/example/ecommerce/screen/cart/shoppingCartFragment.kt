package com.example.ecommerce.screen.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.adapter.cartAdapter
import com.example.ecommerce.databinding.FragmentShoppingCartBinding
import com.example.ecommerce.model.cartProduct
import com.example.ecommerce.screen.home.HomeFragment


class shoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private lateinit var binding : FragmentShoppingCartBinding
    private val cartViewModel : ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver(){
        cartViewModel.items.observe(this){
            binding.rvCartPage.adapter = cartAdapter(it.Data.Cart.Items)

            binding.subtotalPrice.text = it.Data.OrderTotals.SubTotal
            binding.shippingPrice.text = it.Data.OrderTotals.Shipping
            binding.totalPrice.text = it.Data.OrderTotals.OrderTotal
            binding.itemCount.text = it.Data.Cart.Items.size.toString() + " ITEM(S)"
            binding.cartItem.text = it.Data.Cart.Items.size.toString()
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentShoppingCartBinding.bind(view)

        super.onViewCreated(view, savedInstanceState)

        loadData()

        binding.rvCartPage.layoutManager = LinearLayoutManager(requireContext())

        binding.tollBar.setNavigationOnClickListener {
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,HomeFragment()).commit()
            findNavController().popBackStack()
        }
    }

    private fun loadData(){
        cartViewModel.fetchCartProducts()
    }
}