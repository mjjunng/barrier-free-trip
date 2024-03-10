package com.triply.barrierfreetrip.api

import android.content.pm.PackageManager
import com.triply.barrierfreetrip.BFTApplication
import com.triply.barrierfreetrip.data.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginInstance {
    val appInfo = BFTApplication.ApplicationContext().applicationContext.packageManager.getApplicationInfo(
        BFTApplication.ApplicationContext().applicationContext.packageName, PackageManager.GET_META_DATA
    )
    // API keys for social media login
    val KAKAO_KEY = appInfo.metaData.getString("KAKAO_KEY").toString()
    val KAKAO_BASE_URL = "https://kauth.kakao.com/oauth"
    val KAKAO_REDIRECT_URL = "$BASE_URL/oauth/kakao"

    val NAVER_KEY = appInfo.metaData.getString("NAVER_KEY").toString()
    val NAVER_BASE_URL = "https://nid.naver.com/oauth2.0"
    val NAVER_REDIRECT_URL = "$BASE_URL/oauth/naver"

    fun getKakaoApi() : LoginApi {
        return Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LoginApi::class.java)
    }

    fun getNaverApi() : LoginApi {
        return Retrofit.Builder()
            .baseUrl(NAVER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LoginApi::class.java)
    }
}

