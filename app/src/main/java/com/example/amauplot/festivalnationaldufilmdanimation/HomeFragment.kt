package com.example.amauplot.festivalnationaldufilmdanimation

import android.graphics.BitmapFactory
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewMovieShort: RecyclerView = this.activity.findViewById(R.id.recyclerview_moviesshort)
        recyclerViewMovieShort.layoutManager = LinearLayoutManager(this.activity, LinearLayout.HORIZONTAL, false)

        val movies = ArrayList<ItemMovieShort>()

        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))
        movies.add(ItemMovieShort(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30"))

        val adapterMovieShort = MovieHomeViewAdapter(movies)
        recyclerViewMovieShort.adapter = adapterMovieShort
    }
}