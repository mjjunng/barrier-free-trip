package com.triply.barrierfreetrip

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import kotlin.math.roundToInt

class BFTDotIndicator: LinearLayoutCompat {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val smallDotSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7F, context.resources.displayMetrics)
    private val largeDotSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12F, context.resources.displayMetrics)
    private val indicatorGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4F, context.resources.displayMetrics)

    private val indicators = mutableListOf<View>()

    private var pageSize: Int = 0

    /**
     * 뷰페이저 아이템 개수에 따른 인디케이터 초기화
     *
     * @param size 뷰페이저 아이템 개수
     */
    fun initIndicators(size: Int) {
        if (size <= 0) return

        removeAllViews()
        pageSize = size
        for (i in 0 until pageSize) {
            val indicator = AppCompatImageView(context).apply {
                layoutParams = LayoutParams(smallDotSize.roundToInt(), smallDotSize.roundToInt()).apply {
                    if (i < pageSize-1) {
                        marginEnd = indicatorGap.roundToInt()
                    }
                    gravity = Gravity.CENTER_VERTICAL
                }
                setBackgroundColor(resources.getColor(R.color.transparent, null))
                setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.indicator_shape_circle_selected, null))
                alpha = 0.5F
            }

            indicators.add(indicator)
        }

        with(indicators.first()) {
            layoutParams.apply {
                width = largeDotSize.roundToInt()
                height = largeDotSize.roundToInt()
            }
            gravity = Gravity.CENTER_VERTICAL
            alpha = 1F
        }

        indicators.forEach {
            addView(it)
        }
    }

    /**
     * 현재 뷰페이저의 페이지에 해당하는 position값의 indicator 활성화
     * position값은 뷰페이저 아이템의 현재 선택된 position
     *
     * @param position
     */
    fun setCurrentIndicator(position: Int) {
        if (position > pageSize-1 || pageSize == 0) return

        indicators.forEachIndexed { idx, indicator ->
            indicator.apply {
                if (idx == position) {
                    layoutParams = LayoutParams(largeDotSize.roundToInt(), largeDotSize.roundToInt()).apply {
                        if (position < pageSize-1) marginEnd = indicatorGap.roundToInt()
                    }
                    alpha = 1F
                    gravity = Gravity.CENTER_VERTICAL
                } else {
                    layoutParams = LayoutParams(smallDotSize.roundToInt(), smallDotSize.roundToInt()).apply {
                        if (idx < pageSize-1) {
                            marginEnd = indicatorGap.roundToInt()
                        }
                    }
                    alpha = 0.5F
                    gravity = Gravity.CENTER_VERTICAL
                }
                indicator.invalidate()
            }
        }
    }

    init {
        orientation = HORIZONTAL
    }
}