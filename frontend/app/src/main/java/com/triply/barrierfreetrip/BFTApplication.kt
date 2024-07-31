package com.triply.barrierfreetrip

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import com.kakao.sdk.common.KakaoSdk
import com.triply.barrierfreetrip.api.LoginInstance
import com.triply.barrierfreetrip.feature.ApikeyStoreModule

class BFTApplication : Application() {
    private lateinit var keyStore: ApikeyStoreModule
    private val appInfo = applicationContext().applicationContext.packageManager.getApplicationInfo(
        applicationContext().applicationContext.packageName, PackageManager.GET_META_DATA
    )
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
        KakaoSdk.init(this, appInfo.metaData.getString("KAKAO_KEY").toString())
    }

    fun getKeyStore() : ApikeyStoreModule = keyStore
}