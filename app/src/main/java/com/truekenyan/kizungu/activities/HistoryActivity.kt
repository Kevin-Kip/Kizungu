package com.truekenyan.kizungu.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.truekenyan.kizungu.R
import com.truekenyan.kizungu.adapters.HistoryAdapter
import com.truekenyan.kizungu.interfaces.ClickListener
import com.truekenyan.kizungu.models.Word
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), ClickListener {

    private var historyAdapter: HistoryAdapter? = null
    private val wordList = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        historyAdapter = HistoryAdapter(wordList)
        history.apply {
            hasFixedSize()
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.clear_history -> historyAdapter!!.clearItems()
        }
        return true
    }

    override fun onItemCLicked(wordId: Int?) {

    }
}
