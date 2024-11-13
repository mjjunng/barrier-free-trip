package com.triply.barrierfreetrip.feature

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApikeyStoreModule(private val context: Context) {
    private val Context.keyStore:
            DataStore<Preferences> by preferencesDataStore(name = "keyStore")
    private val apiKey = stringPreferencesKey("apikey")

    val getApiKey: Flow<String> = context.keyStore.data
        .map { value: Preferences -> value[apiKey] ?: "" }

    suspend fun setApiKey(key : String) {
        context.keyStore.edit { preferences ->
            preferences[apiKey] = key
        }
    }
}