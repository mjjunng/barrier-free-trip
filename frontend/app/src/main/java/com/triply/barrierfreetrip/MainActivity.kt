package com.triply.barrierfreetrip

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.triply.barrierfreetrip.databinding.ActivityMainBinding

// Main Activity로서의 기능 + 로그인 기능을 수행합니다.
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apikey : String

        // DataBinding Setting
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        // meta-data에 넣어둔 데이터 접근하는 방법
//        // 대박 오래 걸렸으니까 제발 날려먹지마라
//        applicationContext.packageManager.getApplicationInfo(
//            applicationContext.packageName, PackageManager.GET_META_DATA
//        ).apply {
//            apikey = metaData.getString("com.google.android.geo.API_KEY").toString()
//        }
//        Log.d("AAA", apikey)


        // FragmentContainerView를 사용하여 Nav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        binding.bnvMain.setupWithNavController(navHostFragment.navController)

    }
}