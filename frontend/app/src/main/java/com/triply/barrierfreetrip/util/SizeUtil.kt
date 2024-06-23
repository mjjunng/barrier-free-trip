package com.triply.barrierfreetrip.util

import android.content.Context
import android.util.TypedValue

fun Float.toSp(context: Context): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics)
}

fun Float.toDp(context: Context): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
}