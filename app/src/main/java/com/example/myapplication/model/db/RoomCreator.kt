package com.example.myapplication.model.db

import android.content.Context
import androidx.room.Room

object RoomCreator {

    fun getDb(context: Context):AppDataBace{
        return Room.databaseBuilder(context , AppDataBace::class.java,"this_db")
            .build()
    }
}