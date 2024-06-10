package com.example.ecommerce.screen.cart


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.adapter.cartAdapter
import com.example.ecommerce.databinding.FragmentShoppingCartBinding
import com.example.ecommerce.model.cart.cartProducts.CartProducts
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class shoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private lateinit var binding: FragmentShoppingCartBinding
    private val cartViewModel: ShoppingCartViewModel by viewModels()
    private lateinit var adapter: cartAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = cartAdapter({ item ->
            onRemoveItemClick(item)
        }, { item, value ->
            onUpdateItemClick(item, value)
        })

    }

    private fun initView(it: CartProducts) {
        Constants.currCartItem = it.Data.Cart.Items.size
        var currItems = Constants.currCartItem
        var itemCountText: String
        if (currItems == 1) itemCountText = currItems.toString() + " ITEM"
        else itemCountText = currItems.toString() + " ITEM(S)"

        binding.subtotalPrice.text = it.Data.OrderTotals.SubTotal
        binding.shippingPrice.text = it.Data.OrderTotals.Shipping
        binding.totalPrice.text = it.Data.OrderTotals.OrderTotal
        binding.itemCount.text = itemCountText
        binding.cartItem.text = currItems.toString()
    }

    private fun initObserver() {
        binding.rvCartPage.adapter = adapter

        cartViewModel.items.observe(viewLifecycleOwner) {

            adapter.submitList(it.Data.Cart.Items)

            initView(it)
            binding.scrollView.visibility = View.VISIBLE
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
        }

        cartViewModel.rmvResponse.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products.Data.Cart.Items)

            Toast.makeText(requireContext(), "Item Removed!!", Toast.LENGTH_SHORT).show()
            initView(products)
        }

        cartViewModel.updateResponse.observe(viewLifecycleOwner) { products ->
            Toast.makeText(requireContext(), "Item Quantity Updated!!", Toast.LENGTH_SHORT).show()
            initView(products)
        }

        cartViewModel.showMessage.observe(viewLifecycleOwner) { message ->
            if (message.equals("Success") == false) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun onRemoveItemClick(item: Item) {
        cartViewModel.removeCartProduct(item.Id)
    }

    private fun onUpdateItemClick(item: Item, value: Int) {
        cartViewModel.updateCartProduct(item.Id, value)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartBinding.bind(view)

        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        binding.scrollView.visibility = View.INVISIBLE
        binding.shimmerLayout.startShimmer()

        initObserver()
        loadData()

        binding.rvCartPage.layoutManager = LinearLayoutManager(requireContext())

        binding.checkoutBtn.setOnClickListener {
            Checkout()
        }

        binding.tollBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadData() {
        cartViewModel.fetchCartProducts()
    }

    private fun Checkout() {
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            if (Constants.currCartItem > 0) {
                val action =
                    shoppingCartFragmentDirections.actionShoppingCartFragmentToCheckoutFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Must have at least one item on cart!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(requireContext(), "You have to login to checkOut!!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}