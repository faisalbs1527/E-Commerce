package com.example.ecommerce.screen.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.adapter.categoryAdapter
import com.example.ecommerce.adapter.productAdapter
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.screen.cart.ShoppingCartViewModel
import com.example.ecommerce.screen.product.ProductViewModel
import com.example.ecommerce.utils.ConnectivityUtil
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: ShoppingCartViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        Constants.TOKEN = sharedPreferences.getString("auth_token", null)
        initObserver()

    }

    private fun initObserver() {

        homeViewModel.sliderImages.observe(this, Observer { sliders ->
            val sliderData = sliders?.Data
            for (image in sliderData?.Sliders!!) {
                binding.carousel.addData(
                    CarouselItem(
                        imageUrl = image.ImageUrl
                    )
                )
            }
        })

        homeViewModel.categoryProducts.observe(this, Observer { categoryProducts ->
            binding.rvCategory.adapter = categoryAdapter(categoryProducts.Data) { productData ->
                val action = HomeFragmentDirections.actionHomeFragmentToCategoryListFragment(
                    productData.Products.toTypedArray(),
                    productData.Name
                )
                findNavController().navigate(action)
            }
        })

        homeViewModel.products.observe(this, Observer { productClass ->
            binding.rvFeatureProduct.adapter = productAdapter(productClass.Data, { product ->
                val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(product.Id)
                findNavController().navigate(action)
            },
                { product ->
                    if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
                        productViewModel.addToCart(product.Id)
                    }
                })
        })

        productViewModel.cartResponse.observe(this, Observer { response ->
            Toast.makeText(requireContext(), response.Message, Toast.LENGTH_SHORT).show()
            loadCartItemCount()
        })
    }

    private fun loadCartItemCount() {
        cartViewModel.fetchCartProducts()
        cartViewModel.items.observe(viewLifecycleOwner) { products ->
            Constants.currCartItem = products.Data.Cart.Items.size
            binding.cartItem.text = Constants.currCartItem.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
            loadCartItemCount()
        }
        binding.carousel.registerLifecycle(viewLifecycleOwner)

        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvFeatureProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        loadData()


        binding.iconCart.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }

    }

    private fun loadData() {
        homeViewModel.fetchSliderImages(requireContext())
        homeViewModel.fetchCategoryWiseProducts(requireContext())
        homeViewModel.fetchFeaturedProducts(requireContext())
    }
}