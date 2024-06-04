package com.example.ecommerce.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.adapter.productAdapter
import com.example.ecommerce.adapter.categoryAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

    }

    private fun initObserver() {

        homeViewModel.sliderImages.observe(this, Observer {
            val sliderData = it?.Data
            for (image in sliderData?.Sliders!!) {
                binding.carousel.addData(
                    CarouselItem(
                        imageUrl = image.ImageUrl
                    )
                )
            }
        })

        homeViewModel.categoryProducts.observe(this, Observer {
            binding.rvCategory.adapter = categoryAdapter(it.Data) {
                val action = HomeFragmentDirections.actionHomeFragmentToCategoryListFragment(
                    it.Products.toTypedArray(),
                    it.Name
                )
                findNavController().navigate(action)
            }
        })

        homeViewModel.products.observe(this, Observer { productClass ->
            binding.rvFeatureProduct.adapter = productAdapter(productClass.Data, {
                val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(it.Id)
                findNavController().navigate(action)
            },
                {
                    if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
                        productViewModel.addToCart(it.Id)
                    }
                })
        })

        productViewModel.cartResponse.observe(this, Observer {
            Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
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

        if(ConnectivityUtil.isNetworkAvailable(requireContext())){
            loadCartItemCount()
        }
        binding.carousel.registerLifecycle(viewLifecycleOwner)

        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvFeatureProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        loadData()


        binding.iconCart.setOnClickListener {
            if (ConnectivityUtil.isNetworkAvailable(requireContext())) {
                val action = HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment()
                findNavController().navigate(action)
            }
        }

    }

    private fun loadData() {
        homeViewModel.fetchSliderImages(requireContext())
        homeViewModel.fetchCategoryWiseProducts(requireContext())
        homeViewModel.fetchFeaturedProducts(requireContext())
    }
}