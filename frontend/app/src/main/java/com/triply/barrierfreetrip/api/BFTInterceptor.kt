package com.triply.barrierfreetrip.api

import okhttp3.Interceptor
import okhttp3.Response

class BFTInterceptor(token: String) : Interceptor {
    private val authToken : String = token

    override fun intercept(
        chain: Interceptor.Chain
    ): Response = with(chain) {
        val builder = request().newBuilder()
            .header("Authorization", authToken).build()
        proceed(builder)
    }
}