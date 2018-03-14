package com.example.amauplot.festivalnationaldufilmdanimation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(events : ArrayList<LoadedData>, categories : Array<String>): HomeFragment {
            val args = Bundle()
            args.putParcelableArrayList("events", events)
            args.putStringArray("categories", categories)
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewContainerMovieShort: RecyclerView = this.activity.findViewById(R.id.recyclerview_listof_moviesshort)
        recyclerViewContainerMovieShort.layoutManager = LinearLayoutManager(this.activity, LinearLayout.VERTICAL, false)

        val context = this.activity
        val args = arguments
        val events : ArrayList<LoadedData> = args.getParcelableArrayList<LoadedData>("events")
        val categories : Array<String> = args.getStringArray("categories")

        val container_movies = ArrayList<ArrayList<ItemMovieShort>>()
        val sortedEvents = events.sortedWith(compareBy({ it.day }, { it.startTime }, { it.mins }))

        for (j in 1 until categories.size + 1) {
            val movies = ArrayList<ItemMovieShort>()
            for (i in 0 until sortedEvents.size) {
                val event = sortedEvents.get(i)
                if (event.cat_id == j) {
                    var minutes: String
                    if(event.mins == 0) {
                        minutes = "00"
                    } else {
                        minutes = event.mins.toString()
                    }
                    movies.add(ItemMovieShort(event.image, event.title, event.weekDay + " " + event.startTime + 'h' + minutes, event.url))
                }
            }
            container_movies.add(movies)
        }

        val adapterContainerMovieShort = MovieHomeContainerViewAdapter(categories, container_movies, context)
        recyclerViewContainerMovieShort.adapter = adapterContainerMovieShort
    }
}