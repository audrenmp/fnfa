package com.example.amauplot.festivalnationaldufilmdanimation

/**
 * Created by amauplot on 14/02/2018.
 */

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri

// HOMEPAGE

class MovieHomeViewAdapter(val list:ArrayList<ItemMovieShort>):RecyclerView.Adapter<MovieHomeViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_movies_short_horizontal, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MovieHomeViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : ItemMovieShort){
            val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
            ivMovie.setImageBitmap(data.img)
            val tvTitle:TextView = itemView.findViewById(R.id.tv_movie_title)
            tvTitle.text = data.title
            val tvInfos:TextView = itemView.findViewById(R.id.tv_movie_infos)
            tvInfos.text = data.infos
        }
    }
}


// PAGE INFOS !

class PriceViewAdapter(val list:ArrayList<ItemPrice>):RecyclerView.Adapter<PriceViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_prices_horizontal, parent, false)
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

class PartenaireViewAdapter(val list:ArrayList<ItemPartenaire>):RecyclerView.Adapter<PartenaireViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartenaireViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_partenaires_horizontal, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: PartenaireViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : ItemPartenaire){
            val imageView: ImageView = itemView.findViewById(R.id.iv_logo_partenaire)
            imageView.setImageBitmap(data.logo)

            imageView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse(data.link)
//                startActivity(intent)
            }
        }


    }
}