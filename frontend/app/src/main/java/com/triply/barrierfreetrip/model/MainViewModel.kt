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
import com.triply.barrierfreetrip.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MainViewModel : ViewModel() {
    //    val
    private val retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private val kakaoRetrofit = LocationInstance.getLocationApi()

    private val _reviews by lazy { MutableLiveData(ReviewListDTO(0, emptyList())) }
    val reviews: LiveData<ReviewListDTO>
        get() = _reviews

    private val _isUploadingReviewSucceed by lazy { MutableLiveData(Event(false)) }
    val isUploadingReviewSucceed: LiveData<Event<Boolean>>
        get() = _isUploadingReviewSucceed

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

    fun getChargerInfo(contentId: String) {
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
}