package com.triply.barrierfreetrip

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.roundToInt

class BFTRatingStar: LinearLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var rating = 0
    private var spaceBetweenStar = 2F

    private val stars = Array(5) { idx ->
        AppCompatImageView(context).apply {
            setImageResource(R.drawable.ic_round_star_rate_filled)
            setColorFilter(context.getColor(R.color.review_light_gray), PorterDuff.Mode.SRC_IN)
            if (idx < 4) {
                val layoutParams = LayoutParams(24, 24).apply {
                    width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24F, context.resources.displayMetrics).roundToInt()
                    height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24F, context.resources.displayMetrics).roundToInt()
                    setMargins(
                        0,
                        0,
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spaceBetweenStar, context.resources.displayMetrics).roundToInt(),
                        0
                    )
                }
                this.layoutParams = layoutParams
            }
        }
    }

    init {
        setOnTouchListener { v, event ->
            if (!isClickable) return@setOnTouchListener false

            when (event?.action) {
                MotionEvent.ACTION_MOVE -> {
                    when  {
                        event.x < stars[0].x -> { setRating(0) }
                        else -> {
                            stars.forEachIndexed { idx, star ->
                                if (event.x in star.x..star.x + star.width + spaceBetweenStar) setRating(idx+1)
                                return@forEachIndexed
                            }
                        }
                    }
                }
                MotionEvent.ACTION_DOWN -> {
                    if (event.y !in stars[0].top.toFloat()..stars[0].bottom.toFloat()) return@setOnTouchListener false
                    stars.forEachIndexed { idx, star ->
                        if (event.x in star.x..star.x+star.width+spaceBetweenStar) setRating(idx+1)
                    }
                    performClick()
                }
            }
            true
        }
    }

    fun setRating(rating: Int) {
        if (rating < this.rating) {
            for (i in rating until this.rating) {
                (getChildAt(i) as AppCompatImageView).apply {
                    setColorFilter(context.getColor(R.color.review_light_gray), PorterDuff.Mode.SRC_IN)
                    invalidate()
                }
            }
        } else {
            for (i in this.rating until rating) {
                (getChildAt(i) as AppCompatImageView).apply {
                    setColorFilter(context.getColor(R.color.yellow), PorterDuff.Mode.SRC_IN)
                    invalidate()
                }
            }
        }
        this.rating = rating
    }

    fun getRating() = rating

    init {
        stars.forEach {
            addView(it)
        }
    }
}