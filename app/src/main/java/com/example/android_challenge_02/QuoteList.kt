package com.example.android_challenge_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_challenge_02.databinding.ActivityQuoteListBinding

class QuoteList : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteListBinding

    var layoutManager: RecyclerView.LayoutManager? = null
    var adapter: RecyclerView.Adapter<PrototypeAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        binding = ActivityQuoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Starts ViewModel
        val model: QuoteModel by viewModels()

        binding.randomQuote.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                it.putExtra("QUOTE", "Loading...")
                it.putExtra("AUTHOR", "Loading...")
                startActivity(it)
            }
        }

        layoutManager = LinearLayoutManager(this)

        binding.recyclerView.layoutManager = layoutManager

        adapter = PrototypeAdapter()
        binding.recyclerView.adapter = adapter

        model.getAllFromApi(binding, this)
    }

    fun startQuoteScreen(quote : String, author : String) {
        Intent(this, MainActivity::class.java).also {
            it.putExtra("QUOTE", quote)
            it.putExtra("AUTHOR", author)
            startActivity(it)
        }
    }
}