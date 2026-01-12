package com.triply.barrierfreetrip.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

sealed class RespDocument

data class MetaResponse<out T>(
    val status : String,
    @SerializedName("data")
    val respDocument : T?,
    val message : String?
)

data class LoginDto2(
    val accessToken: String,
    val email: String,
    val nickname: String,
    val refreshToken: String
)

data class LoginDto(
    val accessToken: String,
    val refreshToken: String
)

data class LogoutDto(
    val refreshToken: String
)

data class LoginParameter(
    val serviceUserId : String,
    val email : String,
    val nickname : String
)

data class RefreshResponse(
    val accessToken: String,
    val dMessage: String
) : RespDocument()

data class accessToken(val token : String) : RespDocument()

data class TourFacilityDetail(
    val addr1: String = "",
    val addr2: String = "",
    val areaCode: String = "",
    val braileblock: String? = "",
    val checkInTime: String = "",
    val checkOutTime: String = "",
    val contentId: String = "",
    val contentTypeId: String = "",
    val elevator: String? = "",
    val freeParking: String? = "",
    val handicapetc: String? = "",
    val homepage: String = "",
    val imgs: List<String> = listOf(),
    val like: Int = 0, // 0 -> 찜X | 1 -> 찜O
    val mapx: String = "",
    val mapy: String = "",
    val overview: String = "",
    val publictransport: String? = "",
    val rating: String = "",
    val restroom: String? = "",
    val sigunguCode: Any = "",
    val tel: String = "",
    val title: String = "",
) : RespDocument() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
}

data class ErrorDto(
    @SerializedName("emptyResponse")
    val errorCode: String
) : RespDocument()

data class Charger(
    val addr: String,
    val contentId: Int,
    val like: Int,
    val tel: String,
    val title: String
) : RespDocument()

data class ChargerDetail(
    val addr: String = "",
    val air: String = "",
    val holidayClose: String = "",
    val holidayOpen: String = "",
    val like: Int = 0,
    val phoneCharge: String = "",
    val possible: String = "",
    val tel: String = "",
    val title: String = "",
    val weekdayClose: String = "",
    val weekdayOpen: String = "",
    val weekendClose: String = "",
    val weekendOpen: String = "",
) : RespDocument() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
}

data class CareTour(
    val addr: String,
    val like: Int,
    val tel: String,
    val title: String
) : RespDocument()

data class RentalServicePlace(
    val addr: String,
    val like: Int,
    val tel: String,
    val title: String
) : RespDocument()

data class SearchRsltListDto(
    val items: List<SearchRsltItem>
) : RespDocument() {
    data class SearchRsltItem(
        val addr: String,
        val firstImage: String?,
        val rating: String?,
        val tel: String,
        val title: String,
        val type: Int,
        val id: Int,
        val like: Int
    )
}

data class RestPlace(
    val addr: String,
    val contentId: String,
    val contentTypeId: String,
    val firstimg: String,
    val rating: Any,
    val title: String
) : RespDocument()

data class InfoListDto(
    val items: List<InfoListItemDto>
) : RespDocument() {
    data class InfoListItemDto(
        val id: Int,
        val addr: String,
        val like: Boolean,
        val tel: String,
        val title: String
    ) : PickStatusModel() {
        override val uniqueId: String = "list-$id"
        override fun getItemAddress(): String = addr
        override fun getItemTitle(): String = title
        override fun getItemTelephone(): String = tel
        override fun getContentsId(): String = id.toString()
        override fun getLikes(): Boolean = like
    }
}

data class InfoSquareListDto(
    val items: List<InfoSquareItemDto>
) : RespDocument() {
    data class InfoSquareItemDto(
        val addr: String,
        val contentId: String,
        val contentTypeId: String,
        val firstimg: String,
        val like: Boolean,
        val rating: String,
        val tel: String,
        val title: String,
    ) : PickStatusModel() {
        object SquareBind {
            @JvmStatic
            @BindingAdapter("setImage")
            fun setImgUrl(view: ImageView, img: String) {
                Glide.with(view.context)
                    .load(img)
                    .into(view)
            }
        }

        // RecyclerView에 채워넣을 아이템을
        override val uniqueId: String = "square-$contentId"
        override fun getItemAddress(): String = addr
        override fun getItemTitle(): String = title
        override fun getItemTelephone(): String = tel
        override fun getContentsId(): String = contentId
        override fun getLikes(): Boolean = like
    }
}

sealed class PickStatusModel {
    abstract val uniqueId: String

    abstract fun getItemAddress(): String
    abstract fun getItemTitle(): String
    abstract fun getItemTelephone(): String
    abstract fun getContentsId(): String
    abstract fun getLikes(): Boolean
}

data class RegionListDto(
    val items: List<Region>
) : RespDocument() {
    data class Region(
        val code: String,
        val name: String
    )
}
enum class TripType {
    STAY, FACILITY, RESTAURANT, CARE, CHARGER, RENTAL
}

data class ReviewListDTO(
    val totalCnt: Int,
    val reviews: List<ReviewDTO>
) : RespDocument() {
    data class ReviewDTO(
        val nickname: String,
        val rating: Double,
        val content: String,
        val createdDate: String
    )
}

data class ReviewRegistrationDTO(
    val rating: Double,
    val content: String
)

data class ConvenienceInfoDTO(
    val subject: String,
    val content: String
)

//data class LikeDTO(
//    val contentId: ,
//    val contentTypeId: ,
//    val title: ,
//    val addr: ,
//    val rating: ,
//    val firstim: g
//    val tel: ,
//    val like: ,
//)

data class LocationCoordinateDTO(
    val meta: Meta?,
    val documents: List<Document>?
) {
    data class Meta(
        @SerializedName("total_count") val totalCount: Int?,
        @SerializedName("pageable_count") val pageableCount: Int?,
        @SerializedName("is_end") val isEnd: Boolean?
    )

    data class Document(
        @SerializedName("address_name") val addressName: String?,
        @SerializedName("address_type") val addressType: String?,
        @SerializedName("x") val longitude: String?,
        @SerializedName("y") val latitude: String?,
        @SerializedName("address") val address: Address?,
        @SerializedName("road_address") val roadAddress: RoadAddress?
    ) {
        data class Address(
            @SerializedName("address_name") val addressName: String?,
            @SerializedName("region_1depth_name") val region1DepthName: String?,
            @SerializedName("region_2depth_name") val region2DepthName: String?,
            @SerializedName("region_3depth_name") val region3DepthName: String?,
            @SerializedName("region_3depth_h_name") val region3DepthHName: String?,
            @SerializedName("h_code") val hCode: String?,
            @SerializedName("b_code") val bCode: String?,
            @SerializedName("mountain_yn") val mountainYN: String?,
            @SerializedName("main_address_no") val mainAddressNo: String?,
            @SerializedName("sub_address_no") val subAddressNo: String?,
            @SerializedName("x") val longitude: String?,
            @SerializedName("y") val latitude: String?,
        )
        data class RoadAddress(
            @SerializedName("address_name") val addressName: String?,
            @SerializedName("region_1depth_name") val region1DepthName: String?,
            @SerializedName("region_2depth_name") val region2DepthName: String?,
            @SerializedName("region_3depth_name") val region3DepthName: String?,
            @SerializedName("road_name") val roadName: String?,
            @SerializedName("underground_yn") val undergroundYN: String?,
            @SerializedName("main_building_no") val mainBuildingNo: String?,
            @SerializedName("sub_building_no") val subBuildingNo: String?,
            @SerializedName("building_name") val buildingName: String?,
            @SerializedName("zone_no") val zoneNo: String?,
            @SerializedName("x") val longitude: String?,
            @SerializedName("y") val latitude: String?,
        )
    }
}