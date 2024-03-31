package com.triply.barrierfreetrip.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(token: String) : Interceptor {
    private var authToken : String = "Bearer $token"

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val request = request().newBuilder()
            .addHeader("Authorization", authToken)
            .build()
        proceed(request)
    }
}