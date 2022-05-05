package com.example.android_challenge_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LoadAdapter: RecyclerView.Adapter<LoadAdapter.ViewHolder>() {

    private var text = arrayOf("Loading Quotes", "Please Wait")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadAdapter.ViewHolder, position: Int) {
        holder.itemText.text = text[position]
    }

    override fun getItemCount(): Int {
        return text.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemText: TextView
        init {
           itemText = itemView.findViewById(R.id.recycler_quote)
        }
    }

}