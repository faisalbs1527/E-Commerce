package com.example.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerce.databinding.ActivityMainBinding

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment_part)
        bottomNavigationView.setupWithNavController(navController)


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