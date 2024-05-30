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
import com.example.ecommerce.screen.product.ProductViewModel
import com.example.ecommerce.utils.Constants
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel : HomeViewModel by viewModels() {
            HomeViewModelFactory(requireContext().applicationContext)
    }
    private val productViewModel : ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

    }

    private fun initObserver(){

        homeViewModel.sliderImages.observe(this, Observer{
            val sliderData = it?.Data
            for( image in sliderData?.Sliders!!){
                binding.carousel.addData(
                    CarouselItem(
                        imageUrl = image.ImageUrl
                    )
                )
            }
        })

        homeViewModel.categoryProducts.observe(this, Observer {
            binding.rvCategory.adapter = categoryAdapter(it.Data){
                val action = HomeFragmentDirections.actionHomeFragmentToCategoryListFragment(it.Products.toTypedArray(),it.Name)
                findNavController().navigate(action)
//                parentFragmentManager.beginTransaction().replace(R.id.fragment_part,CategoryListFragment(it.Name,it.Products)).commit()
            }
        })

        homeViewModel.products.observe(this, Observer { productClass ->
            binding.rvFeatureProduct.adapter = productAdapter(productClass.Data,{
                val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(it.Id)
                findNavController().navigate(action)
            },
            {
                productViewModel.addToCart(it.Id)
            })
        })

        productViewModel.cartResponse.observe(this, Observer {
            Toast.makeText(requireContext(),it.Message,Toast.LENGTH_SHORT).show()
            loadCartItemCount()
        })
    }

    private fun loadCartItemCount(){
        binding.cartItem.text = Constants.currCartItem.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        loadCartItemCount()

        binding.carousel.registerLifecycle(viewLifecycleOwner)

        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.rvFeatureProduct.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        loadData()

//        populateBestSelling()
//
//        populateFeatured()
//
//        populateSalmon()
//
//        populateFurniture()

        binding.iconCart.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment()
            findNavController().navigate(action)
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,shoppingCartFragment()).commit()
        }

    }

    private fun loadData(){
        homeViewModel.fetchSliderImages()
        homeViewModel.fetchCategoryWiseProducts()
        homeViewModel.fetchFeaturedProducts()
    }



//    private fun populateBestSelling(){
//
//        binding.rvBestselling.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//        products = arrayListOf<productDao>()
//
//        products.add(
//            productDao(
//                R.drawable.orange,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//        products.add(
//            productDao(
//                R.drawable.watch,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//        products.add(
//            productDao(
//                R.drawable.fish,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//        binding.rvBestselling.adapter= productAdapter(products){
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
////            val action = HomeFragmentDirections.actionHomeFragmentToProductFragment()
////            findNavController().navigate(action)
//        }
//    }
//
//    private fun populateFeatured(){
//        binding.rvFeatureProduct.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//        featured = arrayListOf<productDao>()
//
//        featured.add(
//            productDao(
//                R.drawable.feature1,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//        featured.add(
//            productDao(
//                R.drawable.feature2,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//        featured.add(
//            productDao(
//                R.drawable.fish,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//        binding.rvFeatureProduct.adapter= productAdapter(featured){
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
//        }
//    }
//
//    private fun populateSalmon(){
//        binding.rvSalmonFish.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//        salmon = ArrayList<productDao>()
//
//        salmon.add(
//            productDao(
//                R.drawable.salmon1,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//
//        salmon.add(
//            productDao(
//                R.drawable.fish,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//
//        binding.rvSalmonFish.adapter= productAdapter(salmon){
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
//        }
//    }
//
//    private fun populateFurniture(){
//        binding.rvfurniture.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//        furniture = ArrayList<productDao>()
//
//        furniture.add(
//            productDao(
//                R.drawable.fur1,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//
//        furniture.add(
//            productDao(
//                R.drawable.fur2,
//                getString(R.string.item_name),
//                3,
//                getString(R.string.item_price)
//            )
//        )
//
//        binding.rvfurniture.adapter= productAdapter(furniture){
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
//        }
//    }
}