package com.example.myapplication.model.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface TodoDAO {
    @Insert(onConflict =OnConflictStrategy.ABORT)
    suspend fun insertItem(item: TodoItem)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrCreate(item: TodoItem)
    @Delete
    suspend fun delete(item: TodoItem)

    //operation to group
    @Query("SELECT * FROM todoitem ")
    fun getAllItems():LiveData<List<TodoItem>>
    @Query("SELECT * FROM todoitem WHERE isCheck = 1")
    fun getIsCheckdItem():LiveData<List<TodoItem>>
    @Query("SELECT * FROM todoitem WHERE isCheck = 0")
    fun getIsNotCheckd():LiveData<List<TodoItem>>
}