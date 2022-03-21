package com.example.android_challenge_02

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("quotes")
    fun getData(): Call<List<DataItem>>

    @GET("quotes")
    suspend fun responseGetData(): Response<List<DataItem>>
}