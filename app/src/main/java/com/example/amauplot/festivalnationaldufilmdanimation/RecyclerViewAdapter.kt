package com.example.amauplot.festivalnationaldufilmdanimation

/**
 * Created by amauplot on 14/02/2018.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.*

// HOMEPAGE

class MovieHomeContainerViewAdapter(val liststring:Array<String>, var listitems:ArrayList<ArrayList<ItemMovieShort>>, val context:Context): RecyclerView.Adapter<MovieHomeContainerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeContainerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.container_list_of_movies_short_vertical, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MovieHomeContainerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(liststring[position], listitems[position], context)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return liststring.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(title : String, items : ArrayList<ItemMovieShort>, context : Context){
            val tvCategory: TextView = itemView.findViewById(R.id.home_title_category)
            tvCategory.text = title

            val rvListOfMoviesShort: RecyclerView = itemView.findViewById(R.id.recyclerview_moviesshort)
            rvListOfMoviesShort.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

            val adapterMovieShort = MovieHomeViewAdapter(items)
            rvListOfMoviesShort.adapter = adapterMovieShort
        }
    }
}

class MovieHomeViewAdapter(val list:ArrayList<ItemMovieShort>):RecyclerView.Adapter<MovieHomeViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_movies_short_horizontal, parent, false)
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
            val tvTitleMovie:TextView = itemView.findViewById(R.id.tv_movie_title)
            tvTitleMovie.text = data.title
            val tvInfos:TextView = itemView.findViewById(R.id.tv_movie_infos)
            tvInfos.text = data.infos

            val link: TextView = itemView.findViewById(R.id.tv_movie_title)
            link.setOnClickListener({
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse(data.url)
                startActivity(itemView.context, intent, null)
            })
        }
    }
}

// PAGE CALENDRIER

class MovieCalendarViewAdapter(val list:ArrayList<ItemMovieLong>, val context: Context):RecyclerView.Adapter<MovieCalendarViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCalendarViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_movies_full_vertical, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MovieCalendarViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : ItemMovieLong){
            val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
            ivMovie.setImageBitmap(data.img)
            val tvTitle:TextView = itemView.findViewById(R.id.tv_calendar_titre)
            tvTitle.text = data.title
            val tvLieu:TextView = itemView.findViewById(R.id.tv_calendar_lieu)
            tvLieu.text = data.lieu
            val tvPublic:TextView = itemView.findViewById(R.id.tv_calendar_public)
            tvPublic.text = data.public
            val tvHeure:TextView = itemView.findViewById(R.id.tv_calendar_heure)
            tvHeure.text = data.heure
            val tvType:TextView = itemView.findViewById(R.id.tv_calendar_type)
            tvType.text = data.type
            val tvDate:TextView = itemView.findViewById(R.id.tv_calendar_date)
            tvDate.text = data.date

            val btnFav: ImageButton = itemView.findViewById(R.id.favoris)
            btnFav.setOnClickListener({
                (itemView.context as MainActivity).setFavorites(data.id)
                (itemView.context as MainActivity).switchFavoriteBtn(data.id, itemView.findViewById(R.id.favoris))
            })
            (itemView.context as MainActivity).switchFavoriteBtn(data.id, itemView.findViewById(R.id.favoris))

        }
    }
}

// PAGE FAVORIS

class FavoriteCalendarViewAdapter(val list:ArrayList<ItemMovieLong>, val context: Context):RecyclerView.Adapter<FavoriteCalendarViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCalendarViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_movies_full_vertical, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: FavoriteCalendarViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position], position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : ItemMovieLong, position: Int){
            val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
            ivMovie.setImageBitmap(data.img)
            val tvTitle:TextView = itemView.findViewById(R.id.tv_calendar_titre)
            tvTitle.text = data.title
            val tvLieu:TextView = itemView.findViewById(R.id.tv_calendar_lieu)
            tvLieu.text = data.lieu
            val tvPublic:TextView = itemView.findViewById(R.id.tv_calendar_public)
            tvPublic.text = data.public
            val tvHeure:TextView = itemView.findViewById(R.id.tv_calendar_heure)
            tvHeure.text = data.heure
            val tvType:TextView = itemView.findViewById(R.id.tv_calendar_type)
            tvType.text = data.type
            val tvDate:TextView = itemView.findViewById(R.id.tv_calendar_date)
            tvDate.text = data.date

            val btnFav: ImageButton = itemView.findViewById(R.id.favoris)
            btnFav.setOnClickListener({
                list.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, list.size)

                (itemView.context as MainActivity).removeFavoritesFromFavoritesPage(data.id)
            })
            (itemView.context as MainActivity).switchFavoriteBtn(data.id, itemView.findViewById(R.id.favoris))
        }
    }
}

// PAGE INFOS

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
            val tvPrice:TextView = itemView.findViewById(R.id.tv_calendar_titre)
            tvPrice.text = data.price
            val tvDesc:TextView = itemView.findViewById(R.id.tv_calendar_duree)
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

            itemView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse(data.link)
                startActivity(imageView.context, intent, null)
            }
        }
    }
}