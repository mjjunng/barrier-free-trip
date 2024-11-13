package com.triply.barrierfreetrip.adapter.decoration

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.adapter.HomeInfoAdapter
import kotlin.math.roundToInt

class HomeListItemViewHolderDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        when ((parent.adapter as HomeInfoAdapter).getItemViewType(position)) {
            HomeInfoAdapter.VIEWTYPE_TITLE -> {
                outRect.top = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9F, parent.context.resources.displayMetrics).roundToInt()
            }
            HomeInfoAdapter.VIEWTYPE_NEARBY_STAY -> {
                outRect.left = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    if (position%2 == 0) 24F else 8F,
                    parent.context.resources.displayMetrics
                ).roundToInt()

                outRect.right = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    if (position%2 == 0) 8F else 24F,
                    parent.context.resources.displayMetrics
                ).roundToInt()

                outRect.top = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    if (position <= 3) 12F else 8F,
                    parent.context.resources.displayMetrics
                ).roundToInt()

                outRect.bottom = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    if (position < (parent.adapter as HomeInfoAdapter).infoList.filterIsInstance<HomeInfoAdapter.HomeInfoDTO.InfoSquare>().size) 8F else 32F,
                    parent.context.resources.displayMetrics
                ).roundToInt()
            }
        }
    }
}