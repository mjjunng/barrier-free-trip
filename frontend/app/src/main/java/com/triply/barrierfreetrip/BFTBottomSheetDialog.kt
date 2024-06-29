package com.triply.barrierfreetrip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewPropertyAnimator
import androidx.constraintlayout.widget.ConstraintLayout
import com.triply.barrierfreetrip.databinding.DialogChargerInfoBinding

class BFTBottomSheetDialog : ConstraintLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var _binding: DialogChargerInfoBinding? = null
    private val binding: DialogChargerInfoBinding
        get() = _binding!!

    init {
        _binding = DialogChargerInfoBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    fun setDialogInfo(
        title: String = "",
        officeHour: String = "",
        location: String = "",
        callNumber: String = "",
        multiCharger: String = "1",
        airChargerCapability: String = "불가",
        phoneChargerCapability: String = "불가",
        like: Boolean = false
    ) {
        with(binding) {
            tvChargerinfoTitle.text = title
            tvChargerinfoOfficeHours.text = officeHour
            tvChargerinfoLocation.text = location
            tvChargerinfoCall.text = callNumber
            tvChargerinfoMulticharger.text = multiCharger
            tvChargerinfoAircharger.text = airChargerCapability
            tvChargerinfoPhonecharger.text = phoneChargerCapability
            tbSquareLike.isPressed = like
        }
    }

    fun updateLike(like: Boolean) {
        binding.tbSquareLike.isPressed = like
    }

    fun setOnLikeClick(
        onClickListener: OnClickListener
    ) {
        binding.tbSquareLike.setOnClickListener(onClickListener)
    }
}