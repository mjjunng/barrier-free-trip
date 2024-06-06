package com.triply.barrierfreetrip.util

fun String.toUIString(): String {
    return this.replace("_무장애 편의시설", "").replace("&nbsp;", "\n")
}