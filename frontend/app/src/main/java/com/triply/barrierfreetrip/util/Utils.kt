package com.triply.barrierfreetrip.util

import com.triply.barrierfreetrip.data.FacilityViewType
import com.triply.barrierfreetrip.data.PickType

fun getFacilityViewType(type: PickType): FacilityViewType {
    return if (type == PickType.STAY
        || type == PickType.TOUR
        || type == PickType.RESTAURANT)
        FacilityViewType.SQUARE_TYPE
    else FacilityViewType.LIST_TYPE
}