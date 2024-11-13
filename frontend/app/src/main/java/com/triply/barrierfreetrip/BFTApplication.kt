package com.triply.barrierfreetrip

import android.app.Application
import android.content.Context
import com.kakao.vectormap.KakaoMapSdk
import com.triply.barrierfreetrip.feature.ApikeyStoreModule

class BFTApplication : Application() {
    private lateinit var keyStore: ApikeyStoreModule

    companion object {
        private lateinit var instance: BFTApplication
        fun getInstance(): BFTApplication = instance
        fun ApplicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        keyStore = ApikeyStoreModule(this)
        KakaoMapSdk.init(this, BuildConfig.KAKAO_MAP_KEY)
    }

    fun getKeyStore(): ApikeyStoreModule = keyStore
}