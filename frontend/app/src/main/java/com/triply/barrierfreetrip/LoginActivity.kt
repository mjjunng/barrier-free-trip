package com.triply.barrierfreetrip

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.triply.barrierfreetrip.api.LoginApi
import com.triply.barrierfreetrip.api.LoginInstance
import com.triply.barrierfreetrip.data.LoginDto
import com.triply.barrierfreetrip.databinding.ActivityLoginBinding
import com.triply.barrierfreetrip.feature.ApikeyStoreModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    private val splashScreen by lazy { installSplashScreen() }
    private val instance = LoginInstance
    private val kakaoApi: LoginApi by lazy { instance.getKakaoApi() }
    private val naverApi : LoginApi by lazy { instance.getNaverApi() }
    private lateinit var job : Job
    private lateinit var apikeyStoreModule: ApikeyStoreModule
    private lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {true}

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnLoginKakao.setOnClickListener {
            job = lifecycleScope.launch {
                val response = kakaoApi.getToken(
                    instance.KAKAO_KEY, instance.KAKAO_REDIRECT_URL)
                if (response.isSuccessful) {
                    // Datastore든 SharedPreference에 키값 저장
                    // 그걸 메인으로 넘기면서 인텐트에 키값 같이 넘겨주기
                    response.body()?.let {
                            it1 -> apikeyStoreModule.setApiKey(it1.accessToken)
                    }
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "로그인에 실패했습니다.",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        apikeyStoreModule = BFTApplication.getInstance().getKeyStore()

        setContentView(binding.root)
    }

//    private fun setApiKey() {
//        lifecycleScope.launch {
//            apikeyStoreModule.set
//        }
//    }

//    private fun loadApiKey() {
//        lifecycleScope.launch {
//            apikeyStoreModule.setApiKey()
//        }
//    }

}