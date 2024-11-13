package com.triply.barrierfreetrip

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.triply.barrierfreetrip.databinding.ActivityMainBinding
import com.triply.barrierfreetrip.feature.ApikeyStoreModule
import kotlinx.coroutines.launch

// Main Activity로서의 기능 + 로그인 기능을 수행합니다.
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var apikeyStoreModule: ApikeyStoreModule
    private val hasPermissionForCoarseLocation by lazy { ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED }
    private val hasPermissionForFineLocation by lazy { ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED }
    lateinit var accessToken : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DataBinding Setting
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        apikeyStoreModule = BFTApplication.getInstance().getKeyStore()


        // FragmentContainerView를 사용하여 Nav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        binding.bnvMain.setupWithNavController(navHostFragment.navController)

        apikeyStoreModule = BFTApplication.getInstance().getKeyStore()


        if (!hasPermissionForCoarseLocation || !hasPermissionForFineLocation) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)
        }

//        // meta-data에 넣어둔 데이터 접근하는 방법
//        // 대박 오래 걸렸으니까 제발 날려먹지마라
//        applicationContext.packageManager.getApplicationInfo(
//            applicationContext.packageName, PackageManager.GET_META_DATA
//        ).apply {
//            apikey = metaData.getString("com.google.android.geo.API_KEY").toString()
//        }
//        Log.d("AAA", apikey)



    }

    private fun getApikey() {
        lifecycleScope.launch {
            apikeyStoreModule.getApiKey.collect {

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_COARSE_LOCATION -> {
                if (hasPermissionForFineLocation) return

                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)
            }
            else -> {}
        }
    }

    companion object {
        const val CONTENT_ID = "contentId"
        private const val REQUEST_FINE_LOCATION = 0
        private const val REQUEST_COARSE_LOCATION = 1
    }
}