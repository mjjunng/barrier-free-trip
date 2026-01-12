package com.triply.barrierfreetrip.api

import com.triply.barrierfreetrip.data.InfoSquareListDto
import com.triply.barrierfreetrip.data.LogoutDto
import com.triply.barrierfreetrip.data.MetaResponse
import com.triply.barrierfreetrip.data.RespDocument
import com.triply.barrierfreetrip.data.ReviewRegistrationDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BFTApi {
    // API for Login

    // API for Review

    // API for tourist facilities
    // 말 그대로 음식점, 관광 명소, 숙박 시설 다 조회 가능
    @GET("/tourist-facilities/{contentTypeId}/{areaCode}/{sigunguCode}")
    suspend fun getTourFcltList(
        @Path(value = "contentTypeId") typeId : String,
        @Path(value = "areaCode") areaCode : String,
        @Path(value = "sigunguCode") bigPlaceCode : String
    ) : Response<MetaResponse<RespDocument?>>


    @GET("/tourist-facilities/{contentId}")
    suspend fun getTourFcltDetail(
        @Path(value = "contentId") contentId : String
    ) : Response<MetaResponse<RespDocument?>>

    // API for Charger
    @GET("/chargers/{sido}/{sigungu}")
    suspend fun getChargerList(
        @Path(value = "sido") sido: String,
        @Path(value = "sigungu") sigungu: String
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/chargers/info/{contentId}")
    suspend fun getChargerDetail(
        @Path(value = "contentId") contentId: Long
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/care-services/{sido}/{sigungu}")
    suspend fun getCareTourList(
        @Path(value = "sido") bigPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/rentals/{sido}/{sigungu}")
    suspend fun getRentalServiceList(
        @Path(value = "sido") bigPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/search/{keyword}")
    suspend fun getSearchResult(
        @Path(value = "keyword") keyword : String
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/near-hotels/{userX}/{userY}")
    suspend fun getStayList(
        @Path(value = "userX") userX : Double,
        @Path(value = "userY") userY : Double
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/near-chargers/{userX}/{userY}")
    suspend fun getNearbyChargerList(
        @Path(value = "userX") userX : Double,
        @Path(value = "userY") userY : Double
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/sido")
    suspend fun getSidoCode(
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/sido/{sidoCode}")
    suspend fun getSigunguCode(
        @Path(value = "sidoCode") sidoCode : String,
    ) : Response<MetaResponse<RespDocument?>>

    @GET("/reviews/{contentId}")
    suspend fun getReviews(
        @Path(value = "contentId") contentId: String
    ): Response<MetaResponse<RespDocument?>>

    @POST("/reviews/{contentId}")
    suspend fun postReview(
        @Path(value = "contentId") contentId: String,
        @Body body: ReviewRegistrationDTO
    ): Response<Unit>

    @GET("/heart/{type}/{contentId}/{likes}")
    suspend fun postLikes(
        @Path(value = "type") type: Int,
        @Path(value = "contentId") contentId: String,
        @Path(value = "likes") likes: Int
    ): Response<Unit>

    @GET("/heart/{type}")
    suspend fun getLikes(
        @Path(value = "type") type: Int
    ): Response<MetaResponse<RespDocument?>>

    @POST("/mylogout")
    suspend fun logout(@Body refreshToken: LogoutDto): Response<MetaResponse<RespDocument>>
}