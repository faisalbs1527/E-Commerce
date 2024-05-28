package com.example.ecommerce.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentCategoryListBinding
import com.example.ecommerce.adapter.productAdapter
import com.example.ecommerce.adapter.productListAdapter
import com.example.ecommerce.model.category.Product
import com.example.ecommerce.screen.home.HomeFragment
import com.example.ecommerce.model.categoryDao
import com.example.ecommerce.model.productDao
import com.example.ecommerce.screen.home.HomeFragmentDirections
import com.example.ecommerce.screen.product.ProductFragment
import com.example.ecommerce.screen.product.ProductFragmentDirections
import com.example.ecommerce.screen.product.ProductViewModel
import com.example.ecommerce.utils.Constants

class CategoryListFragment() : Fragment() {

    private lateinit var binding : FragmentCategoryListBinding
    private val args : CategoryListFragmentArgs by navArgs()
    private val productViewModel : ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    private fun loadCartItemCount(){
        binding.cartItem.text = Constants.currCartItem.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentCategoryListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        populateCategoryList()

        loadCartItemCount()

        binding.title.text = args.CategoryName
        binding.categoryName.text = args.CategoryName

        binding.tollBar.setNavigationOnClickListener {
            findNavController().popBackStack()
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part, HomeFragment()).commit()
        }

        binding.iconCart.setOnClickListener{
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }

        productViewModel.cartResponse.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.Message, Toast.LENGTH_SHORT).show()
            loadCartItemCount()
        }

    }

    private fun populateCategoryList(){

        binding.rvCategoryListFr.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvCategoryListFr.adapter = productListAdapter(args.productList.toList(),{
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToProductFragment(it.Id)
            findNavController().navigate(action)
        },{
            productViewModel.addToCart(it.Id)
        })
    }

}