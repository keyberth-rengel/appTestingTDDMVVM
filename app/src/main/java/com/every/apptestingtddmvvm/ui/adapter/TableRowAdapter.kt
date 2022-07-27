package com.every.apptestingtddmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.every.apptestingtddmvvm.R
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.ui.listener.OnClickListener

class TableRowAdapter(
    private val listPosts: List<PostEntity>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<ViewHolderTable>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTable {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_table, parent, false)
        return ViewHolderTable(view)
    }

    override fun onBindViewHolder(holder: ViewHolderTable, position: Int) {
        val item = listPosts[position]

        with(holder) {
            setValueInRowTable(item, onClickListener)
        }
    }

    override fun getItemCount(): Int = listPosts.size
}