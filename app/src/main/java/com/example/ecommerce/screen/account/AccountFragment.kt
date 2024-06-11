package com.example.ecommerce.screen.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentAccountBinding
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        binding.cartItem.text = Constants.currCartItem.toString()
        binding.button.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Constants.TOKEN = null
            findNavController().popBackStack()
        }
        binding.iconCart.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToShoppingCartFragment()
            findNavController().navigate(action)
        }
    }
}