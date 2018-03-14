package com.example.amauplot.festivalnationaldufilmdanimation

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
        fun newInstance(events : ArrayList<LoadedData>, categories : Array<String>, locations : Array<String>): CalendarFragment {
            val args = Bundle()
            args.putParcelableArrayList("events", events)
            args.putStringArray("categories", categories)
            args.putStringArray("locations", locations)
            val fragment = CalendarFragment()
            fragment.arguments = args
            return fragment
        }
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

        val args = arguments
        val events : ArrayList<LoadedData> = args.getParcelableArrayList<LoadedData>("events")
        val locations : Array<String> = args.getStringArray("locations")
        val categories : Array<String> = args.getStringArray("categories")
        val movies = ArrayList<ItemMovieLong>()

        for (i in 0 until events.size) {
            val event = events.get(i)
            var age : String
            if(event.age == "") {
                age = "tout public"
            } else {
                age = event.age + " ans"
            }
            movies.add(ItemMovieLong(event.id, event.image, event.title, event.weekDay + " " + event.startTime, locations.get(event.location_id), age, event.startTime, categories.get(event.cat_id), event.day + " " + event.month, event.author, event.url))
        }

        val adapterMovieLong = MovieCalendarViewAdapter(movies, activity)
        recyclerViewMovieLong.adapter = adapterMovieLong
    }
}