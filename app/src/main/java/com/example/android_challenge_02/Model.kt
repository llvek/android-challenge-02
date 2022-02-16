package com.example.android_challenge_02

import androidx.lifecycle.ViewModel

class Model : ViewModel() {

    var actualPhilosopher = "Loading..."
    var actualPhrase = "Loading..."

    fun getNewPhrase(){
        //gets new phrase
    }

    fun getAllFromApi(){
        //gets all phrases from api
    }
}