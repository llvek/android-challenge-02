package com.example.android_challenge_02

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    val API_URL = "https://philosophy-quotes-api.glitch.me/"

    //Gets all data from api
    suspend fun loadAllQuotesFromAPI(): Data {
        var dataItem = DataItem(
            "Load failed",
            "Load failed",
            "Load failed",
            "Load failed"
        )
        var loadFailedMessage = Data()
        loadFailedMessage.add(dataItem)

        val endpoint = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
            .create(ApiInterface::class.java)

        val callback = endpoint.responseGetData()
        return when (callback.code()) {
            200 -> return processQuotes(callback.body())
            404 -> {
                println("Network Error")
                return loadFailedMessage
            }
            else -> {
                println("Can't connect with Server")
                return loadFailedMessage
            }
        }
    }

    fun processQuotes(data : List<DataItem>?): Data {
        var temporaryList = Data()
        for (quoteData in data!!) {

            var dataItem = DataItem(
                quoteData.source,
                quoteData.philosophy,
                quoteData.quote,
                quoteData._id
            )

            temporaryList.add(dataItem)
        }
        return temporaryList
    }
}