package com.triply.barrierfreetrip

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.feature.ApikeyStoreModule
import com.triply.barrierfreetrip.feature.EncryptionModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BFTApplication : Application() {
    private lateinit var keyStore: ApikeyStoreModule

    companion object {
        private lateinit var instance : BFTApplication
        fun getInstance() : BFTApplication = instance
        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        keyStore = ApikeyStoreModule(this)

        EncryptionModule.generateSecretKey(ApikeyStoreModule.REFRESH_TOKEN_KEY)

        val appInfo = applicationContext().applicationContext.packageManager.getApplicationInfo(
            applicationContext().applicationContext.packageName, PackageManager.GET_META_DATA
        )

        val kakaoKey = appInfo.metaData.getString("KAKAO_KEY")?.replace("^\"|\"$".toRegex(), "")
        if (kakaoKey == null) {
            Log.wtf("AAA", "kakao key error...cannot get key!!")
            throw IllegalArgumentException("kakao key error...cannot get key!!")
        }
        Log.d("AAA", kakaoKey)
        KakaoSdk.init(this, kakaoKey)
        KakaoMapSdk.init(this, BuildConfig.kakaomap_key)

        CoroutineScope(Dispatchers.IO).launch {
            keyStore.getAccessToken().collect {
                if (it.isEmpty())
                    return@collect
                RetroInstance.createBFTApi(it)
            }
        }
    }

    fun getKeyStore(): ApikeyStoreModule = keyStore
}