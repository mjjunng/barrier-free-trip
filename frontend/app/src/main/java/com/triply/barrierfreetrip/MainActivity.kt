package com.triply.barrierfreetrip

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.triply.barrierfreetrip.api.LoginApi
import com.triply.barrierfreetrip.data.LoginDto
import com.triply.barrierfreetrip.databinding.ActivityMainBinding
import com.triply.barrierfreetrip.feature.ApikeyStoreModule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Main Activity로서의 기능 + 로그인 기능을 수행합니다.
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var apikeyStoreModule: ApikeyStoreModule
    lateinit var accessToken : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding Setting
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        apikeyStoreModule = BFTApplication.getInstance().getKeyStore()


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

    private fun getApikey() {
        lifecycleScope.launch {
            apikeyStoreModule.getApiKey.collect {

            }
        }
    }

    companion object {
        const val CONTENT_ID = "contentId"
    }
}