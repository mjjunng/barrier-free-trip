package com.triply.barrierfreetrip.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.ReviewListDTO
import com.triply.barrierfreetrip.data.ReviewRegistrationDTO
import com.triply.barrierfreetrip.util.Event
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
//    val
    private val retrofit = RetroInstance.getInstance().create(BFTApi::class.java)

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
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}