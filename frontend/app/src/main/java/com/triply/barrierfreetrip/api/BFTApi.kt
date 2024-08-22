package com.triply.barrierfreetrip.api

import com.triply.barrierfreetrip.data.ChargerDetail
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.data.ReviewListDTO
import com.triply.barrierfreetrip.data.ReviewRegistrationDTO
import com.triply.barrierfreetrip.data.SearchRsltItem
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.data.Sigungu
import com.triply.barrierfreetrip.data.TourFacilityDetail
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
    ) : Response<List<InfoSquareDto>>


    @GET("/tourist-facilities/{contentId}")
    suspend fun getTourFcltDetail(
        @Path(value = "contentId") contentId : String
    ) : Response<TourFacilityDetail>

    // API for Charger
    @GET("/chargers/{sido}/{sigungu}")
    suspend fun getChargerList(
        @Path(value = "sido") sido: String,
        @Path(value = "sigungu") sigungu: String
    ) : Response<List<InfoListDto>>

    @GET("/chargers/info/{contentId}")
    suspend fun getChargerDetail(
        @Path(value = "contentId") contentId: Long
    ) : Response<ChargerDetail>

    @GET("/care-services/{sido}/{sigungu}")
    suspend fun getCareTourList(
        @Path(value = "sido") bigPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Response<List<InfoListDto>>

    @GET("/rentals/{sido}/{sigungu}")
    suspend fun getRentalServiceList(
        @Path(value = "sido") bigPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Response<List<InfoListDto>>

    @GET("/search/{keyword}")
    suspend fun getSearchResult(
        @Path(value = "keyword") keyword : String
    ) : Response<List<SearchRsltItem>>

    @GET("/near-hotels/{userX}/{userY}")
    suspend fun getStayList(
        @Path(value = "userX") userX : Double,
        @Path(value = "userY") userY : Double
    ) : Response<List<InfoSquareDto>>

    @GET("/near-chargers/{userX}/{userY}")
    suspend fun getNearbyChargerList(
        @Path(value = "userX") userX : Double,
        @Path(value = "userY") userY : Double
    ) : Response<List<InfoListDto>>

    @GET("/sido")
    suspend fun getSidoCode(
    ) : Response<List<Sido>>

    @GET("/sido/{sidoCode}")
    suspend fun getSigunguCode(
        @Path(value = "sidoCode") sidoCode : String,
    ) : Response<List<Sigungu>>

    @GET("/reviews/{contentId}")
    suspend fun getReviews(
        @Path(value = "contentId") contentId: String
    ): Response<ReviewListDTO>

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
}