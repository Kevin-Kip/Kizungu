package com.truekenyan.kizungu

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null
    private var text: String? = null
    private var currentWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        textToSpeech = TextToSpeech(this@MainActivity, this)
        initListeners()
    }

    private fun initListeners(){
        button_search.setOnClickListener {
            if (text?.isNotEmpty()!!) {
                findWord(text)
            }
        }

        button_clear.setOnClickListener {
            input_word.text.clear()
        }

        button_speak.setOnClickListener {
            speak(text)
        }

        input_word.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                text = s?.trim().toString()
                when (text!!.length){
                    0 -> {
                        scroll_view.visibility = View.GONE
                        nothing_text.visibility = View.VISIBLE
                        button_clear.visibility = View.GONE
                    }
                    else -> {
                        scroll_view.visibility = View.VISIBLE
                        nothing_text.visibility = View.GONE
                        button_clear.visibility = View.VISIBLE
                        word.text = text
                        word_type.text = getString(R.string.adjective)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                button_search.isEnabled = count > 0
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun findWord(text: String?) {

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = textToSpeech?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this@MainActivity, "Language specified not supported", Toast.LENGTH_SHORT).show()
            } else {
                // TODO enable speak button
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speak(text: String?){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (text != null) {
                textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.options_history -> startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
            R.id.options_settings -> startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        textToSpeech?.apply {
            stop()
            shutdown()
        }
        super.onDestroy()
    }
}
