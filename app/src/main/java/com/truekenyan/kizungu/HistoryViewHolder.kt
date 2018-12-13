package com.truekenyan.kizungu

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val pastWord = itemView.findViewById<TextView>(R.id.past_word)
    private val pastWordType = itemView.findViewById<TextView>(R.id.past_word_type)
    private val pastParent = itemView.findViewById<ConstraintLayout>(R.id.item_parent)
    private val listener = pastParent.context as ClickListener
    fun bind(word: Word){
        pastWord.text = word.text
        pastWordType.text = word.type

        pastParent.setOnClickListener(clickListener(word.wordId))
        pastWord.setOnClickListener(clickListener(word.wordId))
        pastWordType.setOnClickListener(clickListener(word.wordId))
    }

    private fun clickListener(id: Int?): View.OnClickListener? {
        return View.OnClickListener {
            listener.onItemCLicked(id)
        }
    }
}