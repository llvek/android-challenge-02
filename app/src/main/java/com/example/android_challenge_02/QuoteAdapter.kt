package com.example.android_challenge_02

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(data : Data, screen : QuoteList) : RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {

    private var quoteData = data
    private var quoteListScreen = screen

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteAdapter.ViewHolder, position: Int) {
        holder.itemQuote.text = quoteData[position].quote
        holder.itemAuthor.text = quoteData[position].source
    }

    override fun getItemCount(): Int {
        return quoteData.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemQuote: TextView
        var itemAuthor: TextView

        init {
            itemQuote = itemView.findViewById(R.id.recycler_quote)
            itemAuthor = itemView.findViewById(R.id.recycler_author)

            itemView.setOnClickListener{
                val position : Int = adapterPosition

                var quote = quoteData[position].quote
                var author = quoteData[position].source

                quoteListScreen.startQuoteScreen(quote, author)
            }
        }
    }
}