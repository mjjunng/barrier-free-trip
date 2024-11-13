package com.triply.barrierfreetrip.util

fun getElapsedTime(pastTime: Long): String {
    val currentTime = System.currentTimeMillis()
    val elapsedSec = currentTime/1000 - pastTime/1000

    // 1시간 미만
    if (elapsedSec < 3600) {
        if (elapsedSec < 60) return "방금 전"
        for (minute in 1..59) {
            if (elapsedSec <= minute*60) return "${minute}분 전"
        }
    }

    // 1시간 이상 하루 미만 미만
    val elapsedHour = elapsedSec/3600
    if (elapsedHour in 1 until 24) {
        for (hour in 1..23) {
            if (elapsedHour in hour until hour+1) return "${hour}시간 전"
        }
    }

    // 하루 이상 한달 미만
    val elapsedDay = elapsedHour/24
    if (elapsedDay in 1 until 30) {
        for (day in 1..29) {
            if (elapsedDay in day until day+1) return "${day}일 전"
        }
    }

    val elapsedMonth = elapsedDay/30
    if (elapsedMonth in 1 until 12) {
        for (month in 1..11) {
            if (elapsedMonth in month until month+1) return "${month}달 전"
        }
    }
    val elapsedYear = elapsedMonth/12
    if (elapsedYear >= 1) {
        for (year in 1..10) {
            if (elapsedYear in year until year+1) return "${year}년 전"
        }
    }
    return ""
}