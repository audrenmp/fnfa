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

class CalendarFragment : Fragment() {
    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_calendar,
                container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewMovieLong: RecyclerView = this.activity.findViewById(R.id.recyclerview_movieslong)
        recyclerViewMovieLong.layoutManager = LinearLayoutManager(this.activity, LinearLayout.VERTICAL, false)

        val movies = ArrayList<ItemMovieLong>()

        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", " 8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))
        movies.add(ItemMovieLong(BitmapFactory.decodeResource(resources, R.drawable.illu1), "Le titre", "lundi 16h30", "cinema", "tout public", "16h30", "spectacle", "8 avril", "bernard"))

        val adapterMovieLong = MovieCalendarViewAdapter(movies)
        recyclerViewMovieLong.adapter = adapterMovieLong
    }
}