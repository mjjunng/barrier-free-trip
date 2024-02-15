package com.triply.barrierfreetrip.api

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.triply.barrierfreetrip.BFTApplication
import com.triply.barrierfreetrip.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    private val BASE_URL = ""

    private var appInfo = BFTApplication.ApplicationContext().applicationContext.packageManager.getApplicationInfo(
        BFTApplication.ApplicationContext().applicationContext.packageName, PackageManager.GET_META_DATA
    )
    private val KAKAO_KEY = appInfo.metaData.getString("KAKAO_KEY").toString()
    private val NAVER_KEY = appInfo.metaData.getString("NAVER_KEY").toString()

    private val client: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() : Retrofit {
        return client
    }
}