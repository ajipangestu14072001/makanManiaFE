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
        val USER_ID_KEY = stringPreferencesKey("id")
    }
    private val dataStore = context.dataStore


    val getToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }


    val getUserId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.USER_ID_KEY] ?: ""
        }


    suspend fun saveTokenAndId(token: String, id: String) {
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
            preferences[PreferencesKey.USER_ID_KEY] = id
        }
    }
}