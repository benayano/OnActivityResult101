package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.model.DataBaceRepository
import com.example.myapplication.model.db.AppDataBace
import com.example.myapplication.view.TodoAdapter
import com.example.myapplication.viewModel.ItemViewData
import com.example.myapplication.viewModel.ItemViewModelFactory
import com.example.myapplication.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ItemViewModelFactory(
            DataBaceRepository(
                Room.databaseBuilder(this, AppDataBace::class.java, "table-item")
                    .build()
                    .todoDao()
            )
        )
    }

    private val itemListAdapter by lazy { TodoAdapter(this::onItemsChanged) }
    private val allItems: Button by lazy { findViewById(R.id.btnGetAll) }
    private val checkItems: Button by lazy { findViewById(R.id.btnSelected) }
    private val notItems: Button by lazy { findViewById(R.id.btnNot) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemListView: RecyclerView = findViewById(R.id.rvMain)
        itemListView.adapter = itemListAdapter

//        viewModel.listAllLiveData.observe(this) { viewState ->
//            itemListAdapter.submitList(viewState.itemsList)
//        }


        viewModel.mainLiveData.observe(this) { viewState ->
            itemListAdapter.submitList(viewState.itemsList)
        }

        allItems.setOnClickListener {
            viewModel.loadAll()
        }
        checkItems.setOnClickListener {
            viewModel.loadChecked()
//            viewModel.listCheckedLiveData.observe(this) { viewState ->
//                itemListAdapter.submitList(viewState.itemsList)
//            }
        }
        notItems.setOnClickListener {
            viewModel.loadNotChecked()
//            viewModel.listNotCheckedLiveData.observe(this) { viewState ->
//                itemListAdapter.submitList(viewState.itemsList)
//            }
        }

        val addItemEditText: EditText = findViewById(R.id.etAddItem)
        val addItemBTN: Button = findViewById(R.id.btnAdd)

        addItemBTN.setOnClickListener {
            if (addItemEditText.text != null) {
                viewModel.createItem(addItemEditText.text.toString())
                addItemEditText.text = null
            }
        }

        itemListAdapter.onItemCliced={itemViewData->
            viewModel.deleteItem(itemViewData = itemViewData)
        }

    }
    private fun loadList(){

    }

    private fun onItemsChanged(itemViewData: ItemViewData) {
        viewModel.onItemChanged(itemViewData)
    }
}