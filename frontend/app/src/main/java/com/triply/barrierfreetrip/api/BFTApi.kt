package com.triply.barrierfreetrip.api

import com.triply.barrierfreetrip.data.CareTour
import com.triply.barrierfreetrip.data.Charger
import com.triply.barrierfreetrip.data.ChargerDetail
import com.triply.barrierfreetrip.data.RentalServicePlace
import com.triply.barrierfreetrip.data.SearchRsltItem
import com.triply.barrierfreetrip.data.TourFacility
import com.triply.barrierfreetrip.data.TourFacilityDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BFTApi {
    // API for Login

    // API for Review

    // API for tourist facilities
    @GET("/tourist-facilities/{contentTypeId}/{areaCode}/{sigunguCode}")
    suspend fun getTourFcltList(
        @Path(value = "contentTypeId") typeId : String,
        @Path(value = "areaCode") areaCode : String,
        @Path(value = "sigunguCode") bigPlaceCode : String
    ) : Call<List<TourFacility>>


    @GET("/tourist-facilities/{contentId}")
    suspend fun getTourFcltDetail(
        @Path(value = "contentId") contentId : String
    ) : Call<TourFacilityDetail>

    // API for Charger
    @GET("/chargers/{areaCode}")
    suspend fun getChargerList(
        @Path(value = "areaCode") areaCode: String
    ) : Call<List<Charger>>

    @GET("/chargers/info/{contentId}")
    suspend fun getChargerDetail(
        @Path(value = "contentId") contentId: String
    ) : Call<ChargerDetail>

    @GET("/care-services/{sido}/{sigungu}")
    suspend fun getCareTourList(
        @Path(value = "sido") bidPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Call<List<CareTour>>

    @GET("/rentals/{sido}/{sigungu}")
    suspend fun getRentalServiceList(
        @Path(value = "sido") bidPlaceCode : String,
        @Path(value = "sigungu") smallPlaceCode : String
    ) : Call<List<RentalServicePlace>>

    @GET("/search/{keyword}")
    suspend fun getSearchResult(
        @Path(value = "keyword") keyword : String
    ) : Call<SearchRsltItem>

    @GET("/near-hotels/{userX}/{userY}")
    suspend fun getStayList(
        @Path(value = "userX") userX : Double,
        @Path(value = "userY") userY : Double
    )
}