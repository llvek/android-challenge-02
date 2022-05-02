package com.example.android_challenge_02

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

fun main() {
    println("Hello")
    GlobalScope.launch {
        var repo = ApiRepository()
        print(repo.loadAllQuotesFromAPI().toString())
    }
    sleep(100000)
}