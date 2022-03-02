package com.example.loginsample.data.repositories

import com.example.loginsample.base.BaseRepository
import com.example.loginsample.data.UserPreferences
import com.example.loginsample.data.network.AuthApi
import java.util.prefs.Preferences

class AuthRepository(private val api:AuthApi, private val preferences: UserPreferences) : BaseRepository() {
    suspend fun login(
        userName : String,
        password : String
    ) = safeApiCall {
        api.login(userName,password)
    }

    suspend fun saveToken(token : String) {
        preferences.saveToken(token)
    }
}