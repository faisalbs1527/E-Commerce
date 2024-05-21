package com.example.ecommerce.screen.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.adapter.cartAdapter
import com.example.ecommerce.databinding.FragmentShoppingCartBinding
import com.example.ecommerce.model.cartProduct
import com.example.ecommerce.screen.home.HomeFragment


class shoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private lateinit var binding : FragmentShoppingCartBinding
    private lateinit var dummyData : ArrayList<cartProduct>

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
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentShoppingCartBinding.bind(view)

        super.onViewCreated(view, savedInstanceState)

        addDummyData()

        binding.tollBar.setNavigationOnClickListener {
//            parentFragmentManager.beginTransaction().replace(R.id.fragment_part,HomeFragment()).commit()
            findNavController().popBackStack()
        }
    }

    private fun addDummyData(){
        binding.rvCartPage.layoutManager = LinearLayoutManager(requireContext())
        dummyData = arrayListOf<cartProduct>()

        dummyData.add(
            cartProduct(
                R.drawable.feature1,
                "California Orange 8 Pcs",
                "$349.0","$699.0",
                5
            )
        )
        dummyData.add(
            cartProduct(
                R.drawable.feature2,
                "California Orange 8 Pcs",
                "$349.0","$699.0",
                5
            )
        )
        dummyData.add(
            cartProduct(
                R.drawable.furniture,
                "California Orange 8 Pcs",
                "$349.0","$699.0",
                5
            )
        )
        dummyData.add(
            cartProduct(
                R.drawable.orange,
                "California Orange 8 Pcs",
                "$349.0","$699.0",
                5
            )
        )
        dummyData.add(
            cartProduct(
                R.drawable.fish,
                "California Orange 8 Pcs",
                "$349.0","$699.0",
                5
            )
        )
        binding.rvCartPage.adapter = cartAdapter(dummyData)
    }
}