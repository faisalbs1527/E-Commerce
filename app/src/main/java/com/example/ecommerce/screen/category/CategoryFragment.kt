package com.example.ecommerce.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentCategoryBinding
import com.example.ecommerce.adapter.categoryAdapter
import com.example.ecommerce.screen.home.HomeViewModel
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding : FragmentCategoryBinding

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

        binding.cartItem.text = Constants.currCartItem.toString()

        loadData()
        binding.rvCategoryFr.layoutManager = GridLayoutManager(requireContext(),3)

        binding.iconCart.setOnClickListener{
            val action = CategoryFragmentDirections.actionCategoryFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun loadData(){
        homeViewModel.fetchCategoryWiseProducts(requireContext())
    }

}