package com.triply.barrierfreetrip

import android.app.Application
import android.content.Context

class BFTApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance : BFTApplication
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }
}