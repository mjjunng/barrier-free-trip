package com.triply.barrierfreetrip

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.core.content.ContextCompat

class BFTSpinner: androidx.appcompat.widget.AppCompatSpinner {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        dropDownWidth = width
        dropDownHorizontalOffset = -100
    }

    private var hasBeenOpened = false

    override fun performClick(): Boolean {
        hasBeenOpened = true
        background = ContextCompat.getDrawable(this.context, R.drawable.bg_spinner_focused)
        return super.performClick()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        if (hasBeenOpened && hasWindowFocus) {
            hasBeenOpened = false
            background = ContextCompat.getDrawable(this.context, R.drawable.bg_spinner_not_focused)
        }
    }

    override fun setDropDownHorizontalOffset(pixels: Int) {
        super.setDropDownHorizontalOffset(0)
    }

    init {
        background = ContextCompat.getDrawable(this.context, R.drawable.bg_spinner_not_focused)
        setPopupBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.shape_round_corner12_white))
    }
}