package com.example.amauplot.festivalnationaldufilmdanimation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class FavoritesFragment : Fragment() {
    companion object {
        fun newInstance(favorites : ArrayList<LoadedData>, categories : Array<String>, locations : Array<String>): FavoritesFragment {
            val args = Bundle()
            args.putParcelableArrayList("favorites", favorites)
            args.putStringArray("categories", categories)
            args.putStringArray("locations", locations)
            val fragment = FavoritesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_favoris,
                container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewMovieLong: RecyclerView = this.activity.findViewById(R.id.recyclerview_movieslong)
        recyclerViewMovieLong.layoutManager = LinearLayoutManager(this.activity, LinearLayout.VERTICAL, false)

        val args = arguments
        val favorites : ArrayList<LoadedData> = args.getParcelableArrayList<LoadedData>("favorites")
        val locations : Array<String> = args.getStringArray("locations")
        val categories : Array<String> = args.getStringArray("categories")
        val movies = ArrayList<ItemMovieLong>()

        val sortedEvents = favorites.sortedWith(compareBy({ it.day }, { it.startTime }, { it.mins }))

        for (i in 0 until sortedEvents.size) {
            val event = sortedEvents.get(i)
            var age : String
            var minutes: String
            if(event.age == "") {
                age = "tout public"
            } else {
                age = event.age + " ans"
            }
            if(event.mins == 0) {
                minutes = "00"
            } else {
                minutes = event.mins.toString()
            }
            movies.add(ItemMovieLong(event.id, event.image, event.title, locations.get(event.location_id - 1), age, event.startTime.toString() + 'h' + minutes, categories.get(event.cat_id - 1), event.day.toString() + " " + event.month, event.url))
        }

        val adapterMovieLong = FavoriteCalendarViewAdapter(movies, context)
        recyclerViewMovieLong.adapter = adapterMovieLong
    }
}