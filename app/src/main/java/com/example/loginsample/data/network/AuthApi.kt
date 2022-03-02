package com.example.loginsample.data.network

import com.example.loginsample.data.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("api/Account/login")
    suspend fun login (
    @Field("UserName") username : String,
    @Field("Password") password : String
    ) : LoginResponse
}