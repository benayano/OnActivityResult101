package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.myapplication.model.DataBaceRepository
import com.example.myapplication.model.db.TodoItem

class MainViewModel(private val repository: DataBaceRepository) : ViewModel() {
    val listAllLiveData = repository
        .getAllItems()
        .map { ItemListViewData(convertToListViewData(it)) }
    val listCheckedLiveData= repository
        .getIsCheckdItem()
        .map { ItemListViewData(convertToListViewData(it)) }
    val listNotChckedLiveData = repository
        .getIsNotCheckd()
        .map { ItemListViewData(convertToListViewData(it)) }

    private fun convertToListViewData(listTodoItem: List<TodoItem>): List<ItemViewData> {
        return listTodoItem.mapNotNull { todoItem ->
            todoItem.takeIf { it.id != null }?.let {
                ItemViewData(
                    id = it.id!!,
                    name = it.name,
                    complete = it.isCheck
                )
            }
        }
    }

    private fun convertToItemModel(itemViewData: ItemViewData) =
        TodoItem(
            itemViewData.id,
            itemViewData.name,
            itemViewData.complete
        )

    fun onItemChenged(itemViewData: ItemViewData){
        suspend {
            repository.updatItem(
                convertToItemModel(itemViewData)
            )
        }
    }

    fun deleteItem(itemViewData: ItemViewData){
        suspend {
            repository.deleteItem(
                convertToItemModel(itemViewData)
            )
        }
    }

    fun creatItem(test:String){
        suspend {
            repository.insertItem(TodoItem(id =null,name =test,isCheck = true))
        }
    }



}