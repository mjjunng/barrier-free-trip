package com.triply.barrierfreetrip.data

enum class PickType(private val _typeValue: Int) {
    /** 숙박 */       STAY(32),
    /** 관광지 */      TOUR(12),
    /** 음식점  */     RESTAURANT(39),
    /** 돌봄시설 */     CARE(2),
    /** 충전기 */      CHARGE(1),
    /** 렌탈 */       RENTAL(3);

    val typeValue : Int get() = _typeValue
}