package com.triply.barrierfreetrip.api

import android.content.pm.ApplicationInfo
import com.triply.barrierfreetrip.data.CareTour
import com.triply.barrierfreetrip.data.Charger
import com.triply.barrierfreetrip.data.ChargerDetail
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.data.RentalServicePlace
import com.triply.barrierfreetrip.data.RestPlace
import com.triply.barrierfreetrip.data.SearchRsltItem
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.data.Sigungu
import com.triply.barrierfreetrip.data.TourFacilityDetail
import retrofit2.Call
import retrofit2.Response
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
        @Path(value = "contentId") contentId: String
    ) : Response<ChargerDetail>

    @GET("/care-services/{sido}/{sigungu}")
    suspend fun getCareTourList(
        @Path(value = "sido") bidPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Response<List<InfoListDto>>

    @GET("/rentals/{sido}/{sigungu}")
    suspend fun getRentalServiceList(
        @Path(value = "sido") bidPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Response<List<InfoListDto>>

    @GET("/search/{keyword}")
    suspend fun getSearchResult(
        @Path(value = "keyword") keyword : String
    ) : Response<SearchRsltItem>

    @GET("/near-hotels/{userX}/{userY}")
    suspend fun getStayList(
        @Path(value = "userX") userX : Double,
        @Path(value = "userY") userY : Double
    ) : Response<List<InfoSquareDto>>

    @GET("/sido")
    suspend fun getSidoCode(
    ) : Response<List<Sido>>

    @GET("/sido/{sidoCode}")
    suspend fun getSigunguCode(
        @Path(value = "sidoCode") sidoCode : String,
    ) : Response<List<Sigungu>>
}