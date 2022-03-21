package com.example.android_challenge_02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewQuoteModel : ViewModel() {
    val API_URL = "https://philosophy-quotes-api.glitch.me/"
    val loadedQuoteData: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    var quoteData = Data()

    suspend fun loadAllQuotesFromAPI(){
        val endpoint = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
            .create(ApiInterface::class.java)

        val callback = endpoint.responseGetData()
        return when (callback.code()) {
            200 -> quoteData = processQuotes(callback.body())
            404 -> println("Network Error")
            else -> println("Can't connect with Server")
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