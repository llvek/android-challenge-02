package com.example.android_challenge_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_challenge_02.databinding.ActivityQuoteListBinding
import kotlinx.coroutines.launch

class QuoteList : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        binding = ActivityQuoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Starts ViewModel
        val model: RepositoryModel by viewModels()

        val screen = this
        model.quoteData.observe(this) { data ->
            Log.d("Captura", "Meu Dado ${data}")
            var linearLayout = LinearLayoutManager(screen)
            binding.recyclerView.apply {
                adapter = QuoteAdapter(data, screen); layoutManager = linearLayout
            }
        }

        binding.randomQuote.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                it.putExtra("QUOTE", "Loading...")
                it.putExtra("AUTHOR", "Loading...")
                startActivity(it)
            }
        }

        var linearLayout = LinearLayoutManager(this)
        binding.recyclerView.apply{ adapter = LoadAdapter(); layoutManager = linearLayout }

        lifecycleScope.launch {
            model.loadQuotes()
        }

    }

    fun startQuoteScreen(quote : String, author : String) {
        Intent(this, MainActivity::class.java).also {
            it.putExtra("QUOTE", quote)
            it.putExtra("AUTHOR", author)
            startActivity(it)
        }
    }
}