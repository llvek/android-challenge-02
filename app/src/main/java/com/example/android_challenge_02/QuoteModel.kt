package com.example.android_challenge_02

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.android_challenge_02.databinding.ActivityQuoteListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteModel : ViewModel() {

    val API_URL = "https://philosophy-quotes-api.glitch.me/"
    var loadedQuoteData = Data()
    var loadingQuotes = true
    var loaded = false

    fun getAllFromApi(binding: ActivityQuoteListBinding, activity: QuoteList) {
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
                for (quoteData in responseBody) {

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

                setNewRecyclerView(binding, loadedQuoteData, activity)
            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                loadingQuotes = false
                Log.e("Failed", "API Request Failed")
            }

        })
    }

    fun setNewRecyclerView(binding: ActivityQuoteListBinding, data : Data, activity: QuoteList) {
        binding.recyclerView.adapter = QuoteAdapter(data, activity)
        binding.reloadList.visibility = View.INVISIBLE
    }
}