package com.example.myapplication.model

import androidx.lifecycle.LiveData
import com.example.myapplication.model.db.TodoDAO
import com.example.myapplication.model.db.TodoItem

class DataBaceRepository(private val todoDAO: TodoDAO) {

    fun getAllItems() = todoDAO.getAllItems()
    fun getIsCheckdItem() = todoDAO.getIsCheckdItem()
    fun getIsNotCheckd() = todoDAO.getIsNotCheckd()

    suspend fun insertItem(item: TodoItem) = todoDAO.insertItem(item)
    suspend fun updatItem(item: TodoItem) = todoDAO.updateOrCreate(item)
    suspend fun deleteItem(item: TodoItem)= todoDAO.delete(item)
}