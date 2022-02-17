package com.example.android_challenge_02

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_challenge_02.databinding.ActivityMainBinding
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Starts ViewModel
        val model: Model by viewModels()

        binding.randomQuote.text = model.actualQuote
        binding.philosopher.text = model.actualPhilosopher

        binding.newQuoteButton.setOnClickListener{newQuoteClicked(model)}

        //loadQuotesFromApi(model)
        model.getAllFromApi()
    }

    fun loadQuotesFromApi(model : Model) {
        if(!model.loaded) {
            model.getAllFromApi()
        }
    }

    fun newQuoteClicked(model : Model){
        model.getNewQuote()
        binding.randomQuote.text = model.actualQuote
        binding.philosopher.text = model.actualPhilosopher
    }
}