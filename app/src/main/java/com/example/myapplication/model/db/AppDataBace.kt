package com.example.myapplication.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class],version = 1)
abstract class AppDataBace :RoomDatabase(){
    abstract fun todoDao() : TodoDAO
}