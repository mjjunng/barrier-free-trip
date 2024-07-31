package com.triply.barrierfreetrip.api

import com.triply.barrierfreetrip.data.LoginDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginApi {
    @GET("/authorize")
    suspend fun getToken(
        @Query(value = "client_id") cliendId : String,
        @Query(value = "redirect_uri") url : String,
        @Query(value = "response_type") type : String
    ) : Response<LoginDto>

    @GET("/authorize")
    suspend fun getTokenWithHTML(
        @Query(value = "client_id") cliendId : String,
        @Query(value = "redirect_uri") url : String,
        @Query(value = "response_type") type : String
    ) : Response<ResponseBody>
}