package com.example.android_challenge_02

import androidx.lifecycle.ViewModel
import com.example.android_challenge_02.ApiInterface
import com.example.android_challenge_02.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Model : ViewModel() {

    val API_URL = "https://philosophy-quotes-api.glitch.me/"

    var loadedQuoteData = Data()
    var actualPhilosopher = "Loading..."
    var actualQuote = "Loading..."

    var loadingQuotes = true
    var loaded = false

    fun getNewQuote() {
        var quoteData = DataItem("Not Loaded","Not Loaded", "Not Loaded", "Not Loaded")
        if(!loadedQuoteData.isEmpty()){
            quoteData = loadedQuoteData.random()
        }

        actualQuote = quoteData.quote
        actualPhilosopher = quoteData.source

        println("Random Quote:" + actualQuote)
        println("Random Philosopher:" + actualPhilosopher)
    }

    //Get data from API using GET method
    fun getAllFromApi(){
        println("Entered on getAllFromApi Function")
        loadingQuotes = true
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
            .create(ApiInterface::class.java)

        val data = retrofitBuilder.getData()

        data.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>,
                response: Response<List<DataItem>?>
            ) {
                val responseBody = response.body()!!

                println("Data getted from api")

                loadedQuoteData.clear()
                for(quoteData in responseBody){

                    var dataItem = DataItem(
                        quoteData.source,
                        quoteData.philosophy,
                        quoteData.quote,
                        quoteData._id
                    )

                    loadedQuoteData.add(dataItem)
                }

                loadingQuotes = false
                loaded = true
            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                println("Failed to Load Quotes")

                actualQuote = "Failed to Load"
                actualPhilosopher = "Failed to Load"

                loadingQuotes = false
            }
        })
    }
}