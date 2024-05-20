package com.example.ecommerce.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentCategoryBinding
import com.example.ecommerce.screen.adapter.categoryAdapter
import com.example.ecommerce.screen.model.categoryDao
import com.example.ecommerce.screen.model.productDao

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var category : ArrayList<categoryDao>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentCategoryBinding.bind(view)

        super.onViewCreated(view, savedInstanceState)

        populateCategory()
    }

    private fun populateCategory(){
        binding.rvCategoryFr.layoutManager = GridLayoutManager(requireContext(),3)
        category = arrayListOf<categoryDao>()

        category.add(
            categoryDao(
                R.drawable.food,
                "Foods"
            )
        )
        category.add(
            categoryDao(
                R.drawable.watch,
                "Watches"
            )
        )
        category.add(
            categoryDao(
                R.drawable.phone,
                "Phones"
            )
        )
        category.add(
            categoryDao(
                R.drawable.furniture,
                "Furnitures"
            )
        )
        category.add(
            categoryDao(
                R.drawable.shoe,
                "Shoes"
            )
        )
        category.add(
            categoryDao(
                R.drawable.food,
                "Foods"
            )
        )
        category.add(
            categoryDao(
                R.drawable.watch,
                "Watches"
            )
        )
        category.add(
            categoryDao(
                R.drawable.phone,
                "Phones"
            )
        )
        category.add(
            categoryDao(
                R.drawable.furniture,
                "Furnitures"
            )
        )
        category.add(
            categoryDao(
                R.drawable.shoe,
                "Shoes"
            )
        )
        category.add(
            categoryDao(
                R.drawable.food,
                "Foods"
            )
        )
        category.add(
            categoryDao(
                R.drawable.watch,
                "Watches"
            )
        )
        category.add(
            categoryDao(
                R.drawable.phone,
                "Phones"
            )
        )
        category.add(
            categoryDao(
                R.drawable.furniture,
                "Furnitures"
            )
        )
        category.add(
            categoryDao(
                R.drawable.shoe,
                "Shoes"
            )
        )
        binding.rvCategoryFr.adapter = categoryAdapter(category){

        }
    }

}