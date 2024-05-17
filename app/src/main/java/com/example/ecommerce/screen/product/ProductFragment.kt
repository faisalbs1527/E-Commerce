package com.example.ecommerce.screen.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.databinding.FragmentProductBinding
import com.example.ecommerce.screen.home.HomeFragment
import com.example.ecommerce.screen.model.productDao

class ProductFragment(private var product : productDao) : Fragment(R.layout.fragment_product) {

    private lateinit var binding : FragmentProductBinding

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
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentProductBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.productImage.setImageResource(product.productImage)
        binding.productName.text=product.productName

        binding.tollBar.setNavigationOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout,HomeFragment()).commit()
        }
    }

}