package com.truekenyan.kizungu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class HistoryAdapter(private var wordList: List<Word>) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun getItemCount(): Int = wordList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    fun setItems(list: List<Word>){
        wordList = list
        notifyDataSetChanged()
    }
}