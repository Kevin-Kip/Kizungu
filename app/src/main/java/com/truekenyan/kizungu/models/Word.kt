package com.truekenyan.kizungu.models

import java.io.Serializable

data class Word(val wordId: Int? ,val text: String?, val type: String?, val meaning: String?) : Serializable