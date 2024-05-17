package com.example.ecommerce

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerce.databinding.ActivityMainBinding
import com.example.ecommerce.screen.authentication.LoginFragment
import com.example.ecommerce.screen.category.CategoryFragment
import com.example.ecommerce.screen.home.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, HomeFragment())
        transaction.commit()

        binding.bottomNavigationView.setOnItemSelectedListener{ item ->
            when(item.itemId){
                R.id.menu_home -> {

                    if(supportFragmentManager.findFragmentById(R.id.homeFragment) == null){
                        supportFragmentManager.beginTransaction().
                                replace(R.id.frame_layout,HomeFragment()).commit()
                    }
                    true
                }
                R.id.menu_category -> {
                    if(supportFragmentManager.findFragmentById(R.id.categoryFragment) == null){
                        supportFragmentManager.beginTransaction().
                        replace(R.id.frame_layout,CategoryFragment()).commit()
                    }
                    true
                }
                R.id.menu_search ->{
                    true
                }
                R.id.menu_account ->{
                    if(supportFragmentManager.findFragmentById(R.id.loginFragment) == null){
                        supportFragmentManager.beginTransaction().
                        replace(R.id.frame_layout,LoginFragment()).commit()
                    }
                    true
                }
                R.id.menu_more ->{
                    true
                }
                else -> false
            }
        }
    }
}