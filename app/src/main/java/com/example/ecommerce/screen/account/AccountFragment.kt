package com.example.ecommerce.screen.account

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentAccountBinding
import com.example.ecommerce.screen.orderList.OrderListViewModel
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: AccountViewModel by viewModels()
    private val orderListViewModel: OrderListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Constants.TOKEN == null) {
            Toast.makeText(requireContext(), "You have to Logged in first!!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToLoginFragment())
        }

        initObserver()
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() {
        viewModel.response.observe(this) { accountInfo ->
            binding.name.text = accountInfo.Data.FirstName +" "+ accountInfo.Data.LastName
            binding.emailinfo.text = accountInfo.Data.Email
        }
        orderListViewModel.orders.observe(this) { orderList ->
            binding.orderinfo.text = orderList.size.toString()
            var pointsTotal: Int = 0
            for (order in orderList) {
                pointsTotal += order.points.toInt()
            }
            binding.pointsinfo.text = pointsTotal.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        binding = FragmentAccountBinding.bind(view)
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        binding.logout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Constants.TOKEN = null
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    private fun loadData() {
        viewModel.getAccountInfo()
        orderListViewModel.getOrders()
    }
}