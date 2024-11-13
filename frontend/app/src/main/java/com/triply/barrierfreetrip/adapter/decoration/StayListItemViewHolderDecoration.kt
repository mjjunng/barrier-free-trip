package com.triply.barrierfreetrip.adapter.decoration

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class StayListItemViewHolderDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if (position%2 == 0) {
            outRect.left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20F, parent.context.resources.displayMetrics).roundToInt()
            outRect.bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20F, parent.context.resources.displayMetrics).roundToInt()
            outRect.right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, parent.context.resources.displayMetrics).roundToInt()
        } else {
            outRect.left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, parent.context.resources.displayMetrics).roundToInt()
            outRect.bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20F, parent.context.resources.displayMetrics).roundToInt()
            outRect.right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20F, parent.context.resources.displayMetrics).roundToInt()
        }

        if (position in (parent.adapter?.itemCount ?: 0)-2 until (parent.adapter?.itemCount ?: 0)) {
            outRect.bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32F, parent.context.resources.displayMetrics).roundToInt()
        }
    }
}