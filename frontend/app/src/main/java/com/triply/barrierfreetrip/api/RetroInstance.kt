package com.triply.barrierfreetrip.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    val BASE_URL = "http://10.0.2.2:8080"

    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    private var client = builder.build()

    fun getInstance() : Retrofit = client
    fun createBFTApi(token : String) : BFTApi {
        val interceptor = AuthInterceptor(token)

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
            builder.client(httpClient.build())
            client = builder.build()
        }
        return client.create(BFTApi::class.java)
    }
}

