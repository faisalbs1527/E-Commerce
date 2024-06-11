package com.example.ecommerce

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerce.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerce.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment_part)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _,destination,_ ->
            when(destination.id){
                R.id.loginFragment ->{
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.productFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.categoryListFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.shoppingCartFragment ->{
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.checkoutFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}