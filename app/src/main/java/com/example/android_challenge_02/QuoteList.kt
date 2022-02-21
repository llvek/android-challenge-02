package com.example.android_challenge_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android_challenge_02.databinding.ActivityQuoteListBinding

class QuoteList : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        binding = ActivityQuoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Starts ViewModel
        val model: Model by viewModels()

        binding.randomQuote.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}