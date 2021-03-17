package com.example.myapplication.viewModel

data class ItemViewData(
    val id:Int?,
    val name:String,
    val complete:Boolean
)

data class ItemListViewData(
    val ItemsList:List<ItemViewData>
)