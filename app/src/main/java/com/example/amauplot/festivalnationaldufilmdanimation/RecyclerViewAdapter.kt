package com.example.amauplot.festivalnationaldufilmdanimation

/**
 * Created by amauplot on 14/02/2018.
 */

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PriceViewAdapter(val list:ArrayList<ItemPrice>):RecyclerView.Adapter<PriceViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_items_horizontal, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: PriceViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : ItemPrice){
            val tvPrice:TextView = itemView.findViewById(R.id.tv_infos_price)
            tvPrice.text = data.price
            val tvDesc:TextView = itemView.findViewById(R.id.tv_infos_description)
            tvDesc.text = data.desc
        }
    }
}