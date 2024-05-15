package com.example.ecommerce.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityMainBinding
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.screen.adapter.bestSellingAdapter
import com.example.ecommerce.screen.adapter.categoryAdapter
import com.example.ecommerce.screen.model.categoryDao
import com.example.ecommerce.screen.model.productDao
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var products : ArrayList<productDao>
    private lateinit var categorys : ArrayList<categoryDao>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.carousel.registerLifecycle(viewLifecycleOwner)


        addCarouseItem()

        populateCategory()

        populateBestSelling()

    }

    private fun addCarouseItem(){
        val list = mutableListOf<CarouselItem>()

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture1
            )
        )
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture3
            )
        )
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture4
            )
        )

        binding.carousel.setData(list)
    }

    private fun populateCategory(){

        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        categorys = arrayListOf<categoryDao>()

        categorys.add(
            categoryDao(
                R.drawable.food,
                "Foods"
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.watch,
                "Watch"
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.phone,
                "Phone"
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.furniture,
                "Furniture"
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.shoe,
                "Shoe"
            )
        )

        binding.rvCategory.adapter=categoryAdapter(categorys)

    }

    private fun populateBestSelling(){

        binding.rvBestselling.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        products = arrayListOf<productDao>()

        products.add(
            productDao(
                R.drawable.orange,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        products.add(
            productDao(
                R.drawable.watch,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        products.add(
            productDao(
                R.drawable.fish,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        binding.rvBestselling.adapter=bestSellingAdapter(products)
    }
}