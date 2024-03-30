package com.triply.barrierfreetrip

import android.app.Application
import android.content.Context
import com.triply.barrierfreetrip.feature.ApikeyStoreModule

class BFTApplication : Application() {
    private lateinit var keyStore: ApikeyStoreModule
    companion object {
        private lateinit var instance : BFTApplication
        fun getInstance() : BFTApplication = instance
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        keyStore = ApikeyStoreModule(this)
    }

    fun getKeyStore() : ApikeyStoreModule = keyStore
}