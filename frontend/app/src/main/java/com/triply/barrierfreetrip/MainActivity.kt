package com.triply.barrierfreetrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.triply.barrierfreetrip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding Setting
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // FragmentContainerView를 사용하여 Nav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        binding.bnvMain.setupWithNavController(navHostFragment.navController)

    }
}