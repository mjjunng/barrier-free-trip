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
import com.triply.barrierfreetrip.api.LoginApi
import com.triply.barrierfreetrip.api.LoginInstance
import com.triply.barrierfreetrip.data.LoginDto
import com.triply.barrierfreetrip.databinding.ActivityLoginBinding
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
    private val Context.settingDataStore:
            DataStore<Preferences> by preferencesDataStore(name = "setting")
    private val splashScreen by lazy { installSplashScreen() }
    private val instance = LoginInstance
    private val kakaoApi: LoginApi by lazy {
        instance.getKakaoApi()
    }
    private val naverApi : LoginApi by lazy {
        instance.getNaverApi()
    }
    private lateinit var job : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        splashScreen.setKeepOnScreenCondition {true}

        binding.btnLoginKakao.setOnClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = kakaoApi.getToken(
                    instance.KAKAO_KEY, instance.KAKAO_REDIRECT_URL)
                if (response.isSuccessful) {
                    // Datastore든 SharedPreference에 키값 저장
                    // 그걸 메인으로 넘기면서 인텐트에 키값 같이 넘겨주기
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "로그인에 실패했습니다.",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        val scope = CoroutineScope(Dispatchers.Default)

        setContentView(binding.root)
    }
}