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

    init {
        addView(
            DialogChargerInfoBinding.inflate(LayoutInflater.from(context)).root
        )
    }
}