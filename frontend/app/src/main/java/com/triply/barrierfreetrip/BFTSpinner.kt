package com.triply.barrierfreetrip

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

class BFTSpinner: androidx.appcompat.widget.AppCompatSpinner {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        dropDownWidth = width
        getChildAt(0).background = ContextCompat.getDrawable(this.context, R.color.transparent)
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

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        foreground.alpha = if (enabled) 0 else 25
    }

    init {
        background = ContextCompat.getDrawable(this.context, R.drawable.bg_spinner_not_focused)
        foreground = ContextCompat.getDrawable(this.context, R.drawable.bg_spinner_disabled)
        clipToOutline = true
        setPopupBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.shape_round_corner6_white))
        dropDownVerticalOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, context.resources.displayMetrics).roundToInt()
    }
}