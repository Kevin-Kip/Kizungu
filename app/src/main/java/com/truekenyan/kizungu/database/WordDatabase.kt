package com.truekenyan.kizungu.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.truekenyan.kizungu.models.Word
import com.truekenyan.kizungu.utils.Commons

@Database(entities = [(Word::class)], version = 1)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao() : WordDao

    companion object{
        fun getInstance(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                WordDatabase::class.java,
                Commons.DB_NAME).build()
    }
}