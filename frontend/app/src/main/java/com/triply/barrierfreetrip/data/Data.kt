package com.triply.barrierfreetrip.data

data class LoginDto(
    val accessToken: String,
    val email: String,
    val nickname: String,
    val refreshToken: String
)

data class TourFacility(
    val addr: String,
    val contentId: String,
    val contentTypeId: String,
    val firstimg: String,
    val rating: Long,
    val tel: String,
    val title: String
)

data class TourFacilityDetail(
    val _exit: Any,
    val addr1: String,
    val addr2: String,
    val areaCode: String,
    val audioguide: Any,
    val auditorium: Any,
    val babysparechair: Any,
    val bigprint: Any,
    val blindhandicapetc: Any,
    val braileblock: Any,
    val brailepromotion: Any,
    val checkInTime: String,
    val checkOutTime: String,
    val contentId: String,
    val contentTypeId: String,
    val elevator: Any,
    val freeParking: Any,
    val guidehuman: Any,
    val guidesystem: Any,
    val handicapetc: Any,
    val hearinghandicapetc: Any,
    val hearingroom: Any,
    val helpdog: Any,
    val homepage: String,
    val imgs: List<String>,
    val infantsfamilyetc: Any,
    val lactationroom: Any,
    val like: Int, // 0 -> 찜X | 1 -> 찜O
    val mapx: String,
    val mapy: String,
    val overview: String,
    val parking: String,
    val promotion: Any,
    val publictransport: Any,
    val rating: Long,
    val restroom: Any,
    val room: Any,
    val route: Any,
    val signguide: String,
    val sigunguCode: Any,
    val stroller: Any,
    val tel: String,
    val ticketoffice: Any,
    val title: String,
    val videoguide: Any,
    val wheelchair: Any
)

data class Charger(
    val addr: String,
    val contentId: Int,
    val like: Int,
    val tel: String,
    val title: String
)

data class ChargerDetail(
    val addr: String,
    val air: String,
    val holidayClose: String,
    val holidayOpen: String,
    val like: Int,
    val phoneCharge: String,
    val possible: String,
    val tel: String,
    val title: String,
    val weekdayClose: String,
    val weekdayOpen: String,
    val weekendClose: String,
    val weekendOpen: String
)

data class CareTour(
    val addr: String,
    val like: Int,
    val tel: String,
    val title: String
)

data class RentalServicePlace(
    val addr: String,
    val like: Int,
    val tel: String,
    val title: String
)

data class SearchRsltItem(
    val addr: String,
    val firstImage: Any,
    val rating: Any,
    val tel: String,
    val title: String
)

data class RestPlace(
    val addr: String,
    val contentId: String,
    val contentTypeId: String,
    val firstimg: String,
    val rating: Any,
    val title: String
)