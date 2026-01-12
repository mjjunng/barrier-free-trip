package com.triply.barrierfreetrip.api

import com.google.gson.GsonBuilder
import com.triply.barrierfreetrip.BFTApplication
import com.triply.barrierfreetrip.data.MetaResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
//    val BASE_URL = "http://(AWS IP or PC IP):8080"
    val BASE_URL = "http://192.168.0.41:8080"
//    val BASE_URL = "http://175.213.36.21:8080"

    var bftAPI: BFTApi = Retrofit.Builder().baseUrl(BASE_URL).build().create(BFTApi::class.java)
        private set

    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(MetaResponse::class.java, RetrofitDeserializer())
                .create()
        ))
    fun createBFTApi(token : String) {
        val interceptor = AuthInterceptor(token)
        val authenticator = TokenAuthenticator(
            apikeyStoreModule = BFTApplication.getInstance().getKeyStore(),
            loginApi = LoginInstance.loginAPI
        )

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
            httpClient.authenticator(authenticator)
            builder.client(httpClient.build())
        }
        bftAPI = builder.build().create(BFTApi::class.java)
    }
}

