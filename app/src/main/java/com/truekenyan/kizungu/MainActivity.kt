package com.truekenyan.kizungu

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeech = TextToSpeech(this@MainActivity, this)
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
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speak(text: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onDestroy() {
        textToSpeech?.apply {
            stop()
            shutdown()
        }
        super.onDestroy()
    }
}
