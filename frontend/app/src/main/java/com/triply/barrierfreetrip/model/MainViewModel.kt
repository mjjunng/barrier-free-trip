package com.triply.barrierfreetrip.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.LocationInstance
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.ChargerDetail
import com.triply.barrierfreetrip.data.ReviewListDTO
import com.triply.barrierfreetrip.data.ReviewRegistrationDTO
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.data.Sigungu
import com.triply.barrierfreetrip.data.TourFacilityDetail
import com.triply.barrierfreetrip.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private val kakaoRetrofit = LocationInstance.getLocationApi()

    private val _nearbyStayList by lazy { MutableLiveData(listOf<InfoSquareDto>()) }
    val nearbyStayList: LiveData<List<InfoSquareDto>>
        get() = _nearbyStayList

    private val _nearbyChargerList by lazy { MutableLiveData(listOf<InfoSquareDto>()) }
    val nearbyChargerList: LiveData<List<InfoSquareDto>>
        get() = _nearbyChargerList

    private val _sidoCodes by lazy { MutableLiveData(listOf(Sido(code = "-1", name = "시도 선택"))) }
    val sidoCodes: LiveData<List<Sido>>
        get() = _sidoCodes

    private val _sigunguCodes by lazy { MutableLiveData(listOf(Sigungu(code = "-1", name = "구군 선택"))) }
    val sigunguCodes: LiveData<List<Sigungu>>
        get() = _sigunguCodes

    private val _fcltList by lazy { MutableLiveData(listOf<InfoSquareDto>()) }
    val fcltList: LiveData<List<InfoSquareDto>>
        get() = _fcltList

    private val _fcltDetail by lazy { MutableLiveData(TourFacilityDetail()) }
    val fcltDetail: LiveData<TourFacilityDetail>
        get() = _fcltDetail

    private val _reviews by lazy { MutableLiveData(ReviewListDTO(0, emptyList())) }
    val reviews: LiveData<ReviewListDTO>
        get() = _reviews

    private val _isUploadingReviewSucceed by lazy { MutableLiveData(Event(false)) }
    val isUploadingReviewSucceed: LiveData<Event<Boolean>>
        get() = _isUploadingReviewSucceed

    fun getNearbyStayList(userX: Double, userY: Double) {
        viewModelScope.launch {
            try {
                val response = retrofit.getStayList(userX = userX, userY = userY)

                if (response.isSuccessful) {
                    _nearbyStayList.value = response.body() ?: listOf()
                } else {
                    when (response.code()) {

                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getNearbyChargerList(userX: Double, userY: Double) {
        viewModelScope.launch {
            try {
                val response = retrofit.getNearbyChargerList(userX = userX, userY = userY)

                if (response.isSuccessful) {
                    _nearbyChargerList.value = response.body() ?: listOf()
                } else {
                    when (response.code()) {

                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSidoCode() {
        viewModelScope.launch {
            try {
                val response = retrofit.getSidoCode()

                if (response.isSuccessful) {
                    _sidoCodes.value = response.body() ?: listOf(Sido(code = "-1", name = "시도 선택"))
                } else {
                    when (response.code()) {

                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSigunguCode(sidoCode: String) {
        viewModelScope.launch {
            try {
                val response = retrofit.getSigunguCode(sidoCode)

                if (response.isSuccessful) {
                    _sigunguCodes.value = response.body() ?: listOf(Sigungu(code = "-1", name = "구군 선택"))
                } else {
                    when (response.code()) {

                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTourFcltList(type: String, areaCode: String, bigPlaceCode: String) {
        viewModelScope.launch {
            try {
                val response = retrofit.getTourFcltList(typeId = type, areaCode = areaCode, bigPlaceCode = bigPlaceCode)

                if (response.isSuccessful) {
                    _fcltList.value = response.body() ?: listOf()
                } else {
                    when (response.code()) {

                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFcltDetail(contentId: String) {
        viewModelScope.launch {
            try {
                val response = retrofit.getTourFcltDetail(contentId)

                if (response.isSuccessful) {
                    _fcltDetail.value = response.body() ?: TourFacilityDetail()
                } else {
                    when (response.code()) {

                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getReviews(contentId: String) {
        viewModelScope.launch {
            try {
                val response = retrofit.getReviews(contentId)

                if (response.isSuccessful) {
                    _reviews.value = response.body() ?: ReviewListDTO(0, emptyList())
                } else {
                    when (response.code()) {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postReview(contentId: String, rating: Double, content: String) {
        val review = ReviewRegistrationDTO(rating = rating, content = content)

        viewModelScope.launch {
            try {
                val response = retrofit.postReview(contentId = contentId, body = review)

                if (response.isSuccessful) {
                    //
                    _isUploadingReviewSucceed.value = Event(true)
                } else {
                    when (response.code()) {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val _chargerInfo by lazy { MutableLiveData<ChargerDetail>() }
    val chargerInfo: LiveData<ChargerDetail>
        get() = _chargerInfo

    fun getChargerInfo(contentId: Long) {
        viewModelScope.launch {
            try {
                var longitude = 0.0
                var latitude = 0.0

                val response = retrofit.getChargerDetail(contentId = contentId)

                if (response.isSuccessful) {
                    if (!response.body()?.addr.isNullOrEmpty()) {
                        val locationCoordinate = withContext(Dispatchers.IO) {
                            kakaoRetrofit.getLocationCoordinate(address = response.body()!!.addr).body()?.documents?.get(0)
                        }
                        longitude = locationCoordinate?.longitude?.toDouble() ?: 0.0
                        latitude = locationCoordinate?.latitude?.toDouble() ?: 0.0
                    }
                    _chargerInfo.value = response.body() ?: ChargerDetail(
                        addr = "",
                        air = "",
                        holidayClose = "",
                        holidayOpen = "",
                        like = 0,
                        phoneCharge = "",
                        possible = "",
                        tel = "",
                        title = "",
                        weekdayClose = "",
                        weekdayOpen = "",
                        weekendClose = "",
                        weekendOpen = "",
                        latitude = latitude,
                        longitude = longitude
                    )
                } else {
                    when (response.code()) {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postLikes(type: Int, contentId: String, likes: Int) {
        viewModelScope.launch {
            try {
                val response = retrofit.postLikes(type = type, contentId = contentId, likes = likes)
                if (response.isSuccessful) {
                    val chargerInfoResponse = retrofit.getChargerDetail(contentId = contentId.toLong())
                    if (chargerInfoResponse.isSuccessful) {
                        _chargerInfo.value = _chargerInfo.value?.copy(
                            like = chargerInfoResponse.body()?.like ?: 0
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}