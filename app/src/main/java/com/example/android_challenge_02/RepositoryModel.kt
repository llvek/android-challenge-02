package com.example.android_challenge_02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepositoryModel: ViewModel() {

    private val _quoteData = MutableLiveData<Data>()
    val quoteData = _quoteData

    var dataLoaded = false

    suspend fun loadQuotes() {
        var repository = ApiRepository()
        _quoteData.postValue(repository.loadAllQuotesFromAPI())
        dataLoaded = true
    }
}