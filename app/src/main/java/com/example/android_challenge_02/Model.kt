package com.example.android_challenge_02

import androidx.lifecycle.ViewModel
import com.example.android_challenge_02.ApiInterface
import com.example.android_challenge_02.DataItem
import com.example.android_challenge_02.databinding.ActivityMainBinding
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
        if(loadingQuotes){
            quoteData = DataItem("Loading...","Loading...", "Loading...", "Loading...")
        }
        if(!loadedQuoteData.isEmpty()){
            quoteData = loadedQuoteData.random()
        }

        actualQuote = quoteData.quote
        actualPhilosopher = quoteData.source

    }

    //Get data from API using GET method
    fun getAllFromApi(binding: ActivityMainBinding) {
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

                getNewQuote()
                setActualQuote(actualQuote,binding)
                setActualPhilosopher(actualPhilosopher,binding)
            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {

                setActualQuote("Failed to Load", binding)
                setActualPhilosopher("Press the button below", binding)

                loadingQuotes = false
            }
        })
    }

    fun setActualQuote(quote : String, binding : ActivityMainBinding) {
        actualQuote = quote
        binding.randomQuote.text = actualQuote
    }

    fun setActualPhilosopher(philosopher : String, binding : ActivityMainBinding) {
        actualPhilosopher = philosopher
        binding.philosopher.text = actualPhilosopher
    }
}