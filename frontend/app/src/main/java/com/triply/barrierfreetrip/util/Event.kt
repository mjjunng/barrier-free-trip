package com.triply.barrierfreetrip.util

open class Event<out T>(private val `val`: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            `val`
        }
    }

    fun peekContent(): T = `val`
}