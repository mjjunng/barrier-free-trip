package com.triply.barrierfreetrip.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.data.InfoSquareListDto
import com.triply.barrierfreetrip.data.PickStatusModel
import com.triply.barrierfreetrip.data.PickType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.PI

class PickListViewModel(
) : ViewModel() {

    private var bftRetrofit = RetroInstance.bftAPI
    private lateinit var pickType: PickType

    private val _pickListUiState =
        MutableStateFlow<List<PickStatusModel>>(emptyList())
    val pickListUiState = _pickListUiState.asStateFlow()


    fun setPageType(pickType: PickType) {
        this.pickType = pickType
    }

    fun getLikes() {
        viewModelScope.launch {
            try {
                val response = bftRetrofit.getLikes(pickType.typeValue)

                if (response.isSuccessful) {
                    val bodyData = response.body()?.respDocument
                    _pickListUiState.value =
                        when (bodyData) {
                            is InfoSquareListDto -> {
                                bodyData.items
                            }

                            is InfoListDto -> {
                                bodyData.items
                            }

                            else -> emptyList()
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}