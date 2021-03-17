package com.example.myapplication.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val name:String,
    val isCheck:Boolean
)
