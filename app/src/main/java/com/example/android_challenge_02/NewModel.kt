package com.example.android_challenge_02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewModel: ViewModel() {

    private val _quoteData = MutableLiveData<Data>()
    val quoteData = _quoteData

}