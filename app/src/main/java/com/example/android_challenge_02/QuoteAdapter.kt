package com.example.android_challenge_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(data : Data) : RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {

    private var quoteData = data

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
        }
    }
}