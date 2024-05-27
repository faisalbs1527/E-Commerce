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
import com.google.android.material.bottomnavigation.BottomNavigationView

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
                R.id.loginFragment -> {
                    if(sharedPreferences.getBoolean("isLoggedIn",false)){
                        navController.navigate(R.id.accountFragment)
                        binding.bottomNavigationView.visibility = View.VISIBLE
                        Toast.makeText(this,"You have already logged IN",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        binding.bottomNavigationView.visibility = View.GONE
                    }
                }
                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }


//        val fragmentManager = supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.frame_layout, HomeFragment())
//        transaction.commit()

//        binding.bottomNavigationView.setOnItemSelectedListener{ item ->
//            when(item.itemId){
//                R.id.menu_home -> {
//
//                    if(supportFragmentManager.findFragmentById(R.id.homeFragment) == null){
//                        supportFragmentManager.beginTransaction().
//                                replace(R.id.frame_layout,HomeFragment()).commit()
//                    }
//                    true
//                }
//                R.id.menu_category -> {
//                    if(supportFragmentManager.findFragmentById(R.id.categoryFragment) == null){
//                        supportFragmentManager.beginTransaction().
//                        replace(R.id.frame_layout,CategoryFragment()).commit()
//                    }
//                    true
//                }
//                R.id.menu_search ->{
//                    true
//                }
//                R.id.menu_account ->{
//                    if(supportFragmentManager.findFragmentById(R.id.loginFragment) == null){
//                        supportFragmentManager.beginTransaction().
//                        replace(R.id.frame_layout,LoginFragment()).commit()
//                    }
//                    true
//                }
//                R.id.menu_more ->{
//                    true
//                }
//                else -> false
//            }
//        }
    }
}