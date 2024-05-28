package com.example.ecommerce.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentCategoryBinding
import com.example.ecommerce.adapter.categoryAdapter
import com.example.ecommerce.adapter.productAdapter
import com.example.ecommerce.model.categoryDao
import com.example.ecommerce.screen.home.HomeFragmentDirections
import com.example.ecommerce.screen.home.HomeViewModel
import com.example.ecommerce.screen.product.ProductFragmentDirections
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var category : ArrayList<categoryDao>

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
    }

    private fun initObserver(){

        homeViewModel.categoryProducts.observe(this, Observer {
            binding.rvCategoryFr.adapter = categoryAdapter(it.Data){
                val action = CategoryFragmentDirections.actionCategoryFragmentToCategoryListFragment(it.Products.toTypedArray(),it.Name)
                findNavController().navigate(action)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentCategoryBinding.bind(view)

        super.onViewCreated(view, savedInstanceState)

        loadData()
        binding.rvCategoryFr.layoutManager = GridLayoutManager(requireContext(),3)

        binding.iconCart.setOnClickListener{
            val action = CategoryFragmentDirections.actionCategoryFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun loadData(){
        homeViewModel.fetchCategoryWiseProducts()
    }

}