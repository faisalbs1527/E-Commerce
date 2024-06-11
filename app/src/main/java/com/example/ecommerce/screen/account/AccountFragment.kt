package com.example.ecommerce.screen.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        if(Constants.TOKEN == null){
            Toast.makeText(requireContext(), "You have to Logged in first!!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToLoginFragment())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        binding.logout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Constants.TOKEN = null
            findNavController().popBackStack(R.id.homeFragment,false)
        }
    }
}