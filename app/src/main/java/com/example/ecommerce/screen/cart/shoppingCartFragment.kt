package com.example.ecommerce.screen.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.adapter.cartAdapter
import com.example.ecommerce.databinding.FragmentShoppingCartBinding
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.utils.Constants


class shoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private lateinit var binding : FragmentShoppingCartBinding
    private val cartViewModel : ShoppingCartViewModel by viewModels()
    private lateinit var adapter : cartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        adapter = cartAdapter({ item ->
            onRemoveItemClick(item)
        }, { item, value ->
            onUpdateItemClick(item,value)
        })

    }

    private fun initView(it : CartProducts){
        Constants.currCartItem = it.Data.Cart.Items.size
        var currItems = Constants.currCartItem
        var itemCountText : String
        if(currItems == 1) itemCountText= currItems.toString() + " ITEM"
        else itemCountText = currItems.toString() + " ITEM(S)"

        binding.subtotalPrice.text = it.Data.OrderTotals.SubTotal
        binding.shippingPrice.text = it.Data.OrderTotals.Shipping
        binding.totalPrice.text = it.Data.OrderTotals.OrderTotal
        binding.itemCount.text = itemCountText
        binding.cartItem.text = currItems.toString()
    }

    private fun initObserver(){
        binding.rvCartPage.adapter = adapter

        cartViewModel.items.observe(viewLifecycleOwner){

            adapter.submitList(it.Data.Cart.Items)

            initView(it)
            binding.scrollView.visibility = View.VISIBLE
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
        }

        cartViewModel.rmvResponse.observe(viewLifecycleOwner){
            adapter.submitList(it.Data.Cart.Items)

            Toast.makeText(requireContext(),"Item Removed!!",Toast.LENGTH_SHORT).show()
            initView(it)
        }

        cartViewModel.updateResponse.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),"Item Quantity Updated!!",Toast.LENGTH_SHORT).show()
        }

    }

    private fun onRemoveItemClick(item : Item){
        cartViewModel.removeCartProduct(item.Id)
    }

    private fun onUpdateItemClick(item: Item, value : Int){
        cartViewModel.updateCartProduct(item.Id,value)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartBinding.bind(view)

        binding.scrollView.visibility = View.INVISIBLE
        binding.shimmerLayout.startShimmer()

        initObserver()
        loadData()

        binding.rvCartPage.layoutManager = LinearLayoutManager(requireContext())

        binding.tollBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadData(){
        cartViewModel.fetchCartProducts()
    }
}