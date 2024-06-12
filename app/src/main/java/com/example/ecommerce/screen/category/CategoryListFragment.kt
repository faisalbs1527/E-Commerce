package com.example.ecommerce.screen.category

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.adapter.productListAdapter
import com.example.ecommerce.databinding.FragmentCategoryListBinding
import com.example.ecommerce.screen.cart.ShoppingCartViewModel
import com.example.ecommerce.screen.product.ProductViewModel
import com.example.ecommerce.utils.ConnectivityUtil
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment() : Fragment(R.layout.fragment_category_list) {

    private lateinit var binding: FragmentCategoryListBinding
    private val args: CategoryListFragmentArgs by navArgs()
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun loadCartItemCount() {
        cartViewModel.fetchCartProducts()
        cartViewModel.items.observe(viewLifecycleOwner) { products ->
            Constants.currCartItem = products.Data.Cart.Items.size
            binding.cartItem.text = Constants.currCartItem.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentCategoryListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        populateCategoryList()

        binding.cartItem.text = Constants.currCartItem.toString()

        binding.title.text = args.CategoryName
        binding.categoryName.text = args.CategoryName

        binding.tollBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.iconCart.setOnClickListener {
            val action =
                CategoryListFragmentDirections.actionCategoryListFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }

        productViewModel.cartResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
            if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
                loadCartItemCount()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Check you internet connection!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        productViewModel.showMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun populateCategoryList() {

        binding.rvCategoryListFr.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategoryListFr.adapter = productListAdapter(args.productList.toList(), {
            val action =
                CategoryListFragmentDirections.actionCategoryListFragmentToProductFragment(it.Id)
            findNavController().navigate(action)
        }, {
            if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
                productViewModel.addToCart(it.Id)
            }
        })
    }

}