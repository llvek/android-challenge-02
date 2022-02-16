package com.example.android_challenge_02.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model {

    val URL = "https://philosophy-quotes-api.glitch.me/"

    fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build()
            .create(ApiInterface::class.java)

        val data = retrofitBuilder.getData()

        data.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>,
                response: Response<List<DataItem>?>
            ) {
                val responseBody = response.body()!!

                print(responseBody)

                for(quoteData in responseBody){
                    println("Quote:" + quoteData.quote)
                    println("Source: " + quoteData.source)
                    println("Philosophy: " + quoteData.philosophy)
                    println("_id:" + quoteData._id + "\n")
                }
            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                println("Failed to Load Quotes")
            }
        })
    }
}