package com.example.penjualanmakanan.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.penjualanmakanan.repository.DataStoreRepository.PreferencesKey.USER_TOKEN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "makanmania_pref")
class DataStoreRepository(context: Context) {
    private object PreferencesKey {
        val USER_TOKEN_KEY = stringPreferencesKey("token")
    }

    val getToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }


    private val dataStore = context.dataStore
    suspend fun saveToken(name: String) {
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = name
        }
    }
}