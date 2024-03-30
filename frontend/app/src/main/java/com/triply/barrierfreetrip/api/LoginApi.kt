package com.triply.barrierfreetrip.api

import com.triply.barrierfreetrip.data.LoginDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginApi {
    @GET("/authorize")
    suspend fun getToken(
        @Path(value = "client_id") cliendId : String,
        @Path(value = "redirect_uri") url : String,
        @Path(value = "response_type") type : String = "code"
    ) : Response<LoginDto>
}