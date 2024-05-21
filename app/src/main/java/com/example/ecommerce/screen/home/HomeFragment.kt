package com.example.ecommerce.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.adapter.productAdapter
import com.example.ecommerce.adapter.categoryAdapter
import com.example.ecommerce.screen.category.CategoryListFragment
import com.example.ecommerce.model.categoryDao
import com.example.ecommerce.model.productDao
import com.example.ecommerce.screen.cart.shoppingCartFragment
import com.example.ecommerce.screen.product.ProductFragment
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var products : ArrayList<productDao>
    private lateinit var categorys : ArrayList<categoryDao>
    private lateinit var featured : ArrayList<productDao>
    private lateinit var salmon : ArrayList<productDao>
    private lateinit var furniture : ArrayList<productDao>


    private lateinit var foods : ArrayList<productDao>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.carousel.registerLifecycle(viewLifecycleOwner)

        addFooditems()

        addCarouseItem()

        populateCategory()

        populateBestSelling()

        populateFeatured()

        populateSalmon()

        populateFurniture()

        binding.iconCart.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment()
            findNavController().navigate(action)
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,shoppingCartFragment()).commit()
        }

    }

    private fun addFooditems(){
        foods = arrayListOf<productDao>()

        foods.add(
            productDao(
                R.drawable.orange,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        foods.add(
            productDao(
                R.drawable.fish,
                "Slice Fish 8 pcs",
                3,
                "$30.00"
            )
        )
        foods.add(
            productDao(
                R.drawable.salmon1,
                "Salmon fish",
                4,
                "$35.00"
            )
        )
        foods.add(
            productDao(
                R.drawable.food,
                "Fruits 1 kg",
                3,
                "$20.00"
            )
        )
        foods.add(
            productDao(
                R.drawable.feature2,
                "Meat 1 kg",
                4,
                "$28.00"
            )
        )
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
                "Foods",
                foods
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.watch,
                "Watch",
                foods
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.phone,
                "Phone",
                foods
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.furniture,
                "Furniture",
                foods
            )
        )
        categorys.add(
            categoryDao(
                R.drawable.shoe,
                "Shoe",
                foods
            )
        )

        binding.rvCategory.adapter= categoryAdapter(categorys){
            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,CategoryListFragment(it)).commit()
        }

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
        binding.rvBestselling.adapter= productAdapter(products){
            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
//            val action = HomeFragmentDirections.actionHomeFragmentToProductFragment()
//            findNavController().navigate(action)
        }
    }

    private fun populateFeatured(){
        binding.rvFeatureProduct.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        featured = arrayListOf<productDao>()

        featured.add(
            productDao(
                R.drawable.feature1,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        featured.add(
            productDao(
                R.drawable.feature2,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        featured.add(
            productDao(
                R.drawable.fish,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )
        binding.rvFeatureProduct.adapter= productAdapter(featured){
            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
        }
    }

    private fun populateSalmon(){
        binding.rvSalmonFish.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        salmon = ArrayList<productDao>()

        salmon.add(
            productDao(
                R.drawable.salmon1,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )

        salmon.add(
            productDao(
                R.drawable.fish,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )

        binding.rvSalmonFish.adapter= productAdapter(salmon){
            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
        }
    }

    private fun populateFurniture(){
        binding.rvfurniture.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        furniture = ArrayList<productDao>()

        furniture.add(
            productDao(
                R.drawable.fur1,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )

        furniture.add(
            productDao(
                R.drawable.fur2,
                getString(R.string.item_name),
                3,
                getString(R.string.item_price)
            )
        )

        binding.rvfurniture.adapter= productAdapter(furniture){
            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,ProductFragment(it)).commit()
        }
    }
}