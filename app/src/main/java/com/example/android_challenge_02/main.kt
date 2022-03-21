package com.example.android_challenge_02

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Thread.sleep

fun main() {

    val model = NewQuoteModel()
    GlobalScope.launch {
        model.loadAllQuotesFromAPI()
        println(model.quoteData)
    }

    println("Hello World!")

    sleep(100000)
}