package com.example.loginsample.repositories

import com.example.loginsample.base.BaseRepository
import com.example.loginsample.network.AuthApi
import com.example.loginsample.network.RemoteDataSource

class AuthRepository(private val api:AuthApi) : BaseRepository() {
    suspend fun login(
        userName : String,
        password : String
    ) = safeApiCall {
        api.login(userName,password)
    }
}