package com.example.android_challenge_02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_challenge_02.databinding.ActivityMainBinding
import androidx.activity.viewModels
//Single Quote Activity Class
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Starts ViewModel
        val model: Model by viewModels()

        binding.newQuoteButton.setOnClickListener{newQuoteClicked(model)}
        binding.quoteListButton.setOnClickListener{
            val intent = Intent(this, QuoteList::class.java)
            startActivity(intent)
        }

        binding.randomQuote.text = intent.getStringExtra("QUOTE")
        binding.philosopher.text = intent.getStringExtra("AUTHOR")

        if(intent.getStringExtra("QUOTE")=="Loading..."){
            loadQuotesFromApi(model,binding)
        }
    }

    fun loadQuotesFromApi(model : Model,binding: ActivityMainBinding) {
        if(!model.loaded) {
            model.getAllFromApi(binding)
        }
    }

    fun newQuoteClicked(model : Model){
        if(!model.loaded) {
            model.getAllFromApi(binding)
        }
        model.getNewQuote()
        binding.randomQuote.text = model.actualQuote
        binding.philosopher.text = model.actualPhilosopher
    }
}