package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.viewModel.ItemViewData

class TodoAdapter(private val clickListener: (itemViewData: ItemViewData) -> Unit) :
    ListAdapter<ItemViewData, ItemViewHolder>(ItemDifutil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

}


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val itemId: TextView = view.findViewById(R.id.tvId)
    private val itemName: TextView = view.findViewById(R.id.tvName)
    private val checkBox: CheckBox = view.findViewById(R.id.checkBox)

    fun bind(itemViewData: ItemViewData, clickChkBox: (item: ItemViewData) -> Unit) {
        itemId.text = itemViewData.id.toString()
        itemName.text = itemViewData.name
        checkBox.isChecked = itemViewData.complete
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            if (b != itemViewData.complete) {
                clickChkBox.invoke(itemViewData.copy(complete = b))
            }
        }

    }
}

class ItemDifutil : DiffUtil.ItemCallback<ItemViewData>() {
    override fun areItemsTheSame(oldItem: ItemViewData, newItem: ItemViewData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ItemViewData, newItem: ItemViewData): Boolean {
        return oldItem == newItem
    }

}
