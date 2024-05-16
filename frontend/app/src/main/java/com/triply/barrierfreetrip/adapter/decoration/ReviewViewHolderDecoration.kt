package com.triply.barrierfreetrip.adapter.decoration

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class ReviewViewHolderDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if (position == (parent.adapter?.itemCount ?: 1)-1) return
        outRect.bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12F, parent.context.resources.displayMetrics).roundToInt()
    }
}