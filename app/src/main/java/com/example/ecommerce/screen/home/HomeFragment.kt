package com.example.ecommerce.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityMainBinding
import com.example.ecommerce.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.carousel.registerLifecycle(viewLifecycleOwner)

        addCarouseItem()

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
}