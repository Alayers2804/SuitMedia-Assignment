package com.bangkit.suitmediatestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bangkit.suitmediatestapplication.databinding.ActivityMainBinding
import com.bangkit.suitmediatestapplication.databinding.FragmentFirstScreenBinding
import com.bangkit.suitmediatestapplication.databinding.FragmentThirdScreenBinding
import com.bangkit.suitmediatestapplication.ui.navigation.SecondScreenFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onBackPressed() {
        val currentDestination = navController.currentDestination
        when (currentDestination?.id) {
            R.id.firstScreenFragment -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(onBackStackChangedListener)
    }

    private val onBackStackChangedListener = FragmentManager.OnBackStackChangedListener {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
    }

}