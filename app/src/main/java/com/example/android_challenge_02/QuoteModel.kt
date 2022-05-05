package com.example.android_challenge_02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//Holds Main Activity UI State
class QuoteModel : ViewModel() {
    val observableQuote = MutableLiveData<String>("Loading...")
    val observablePhilosopher = MutableLiveData<String>("Loading...")

    fun getRandomQuote(data : MutableLiveData<Data>){
        var quoteData = data.value?.random()
        if(quoteData?.quote==null){
            observableQuote.postValue("Failed to Load")
            observablePhilosopher.postValue("Failed to Load")
        } else {
            observableQuote.postValue(quoteData?.quote)
            observablePhilosopher.postValue(quoteData?.source)
        }
    }

    fun getRandomQuote(data : Data ){
        var quoteData = data.random()
        if(quoteData.quote==null){
            observableQuote.postValue("Failed to Load")
            observablePhilosopher.postValue("Failed to Load")
        } else {
            observableQuote.postValue(quoteData.quote)
            observablePhilosopher.postValue(quoteData.source)
        }
    }
}