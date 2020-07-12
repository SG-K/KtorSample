package com.example.ktorsample.reposearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktorsample.R
import com.example.ktorsample.reposearch.models.ItemsItem
import com.example.ktorsample.utils.extensions.print
import kotlinx.android.synthetic.main.item_repos_adapter.view.*

class ReposAdapter :
    RecyclerView.Adapter<ReposAdapter.modelViewHolder>() {


    var arrayList: ArrayList<ItemsItem>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): modelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.item_repos_adapter, parent, false)
        return modelViewHolder(v, viewType)
    }

    override fun getItemCount(): Int {
        return arrayList?.size ?: 0
    }

    override fun onBindViewHolder(holder: modelViewHolder, position: Int) {
        arrayList?.let {
            holder.onBindView(it[position], position)
        }
    }

    fun setData(users: List<ItemsItem>?) {
        users?.let {
            arrayList?.clear()
            arrayList?.addAll(it)
            "size in adapter ${arrayList?.size}".print()
            notifyDataSetChanged()
        }
    }


    inner class modelViewHolder(
        itemView: View,
        viewType: Int
    ) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun onBindView(data: ItemsItem, position: Int) {
            itemView.apply {
                tv_name?.text = data.name
                tv_stars?.text = "${data.stargazers_count?.toString()} "
            }
        }
    }

}