package com.example.ecommerce.screen.authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentLoginBinding
import com.example.ecommerce.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        initObserver()

    }

    private fun initObserver() {
        viewModel.response.observe(this) { data ->
            editor.putString("auth_token", data.Data.Token)
            editor.putBoolean("isLoggedIn", true)
            editor.putString("email", binding.usernameEt.text.toString().trim())
            Constants.TOKEN = data.Data.Token
            editor.apply()
            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack(R.id.homeFragment, false)
        }
        viewModel.showMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        initListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.homeFragment, false)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }

    private fun initListeners() {
        binding.loginBtn.setOnClickListener {
            viewModel.postLogin(
                binding.usernameEt.text.toString().trim(),
                binding.passwordEt.text.toString().trim()
            )
        }
    }

}