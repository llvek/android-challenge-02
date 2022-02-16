package com.example.android_challenge_02.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("quotes")
    fun getData(): Call<List<DataItem>>
}