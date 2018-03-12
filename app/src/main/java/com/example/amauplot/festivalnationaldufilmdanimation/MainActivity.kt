package com.example.amauplot.festivalnationaldufilmdanimation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList



class MainActivity : FragmentActivity() {

    val data_file = "data.json"
    val favs_file = "favorites.json"
    var events = ArrayList<LoadedData>()
    val favorites = ArrayList<LoadedData>()
    val categories = arrayOf(
        "Séance scolaire ouverte au public",
        "Séance spéciale",
        "Volet professionnel",
        "Compétition et sélections",
        "Long métrage et rencontres",
        "Atelier . secret Fab ",
        "Salon des nouvelles écritures"
    )
    val locations = arrayOf(
        "Cinéma du TNB",
        "L'Arbor"
    )

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_calendar -> {
                 val calendarFragment = CalendarFragment.newInstance(events, categories, locations)
                 switchFragment(R.id.fragment_wrapper, calendarFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favoris -> {
                val favoritesFragment = FavoritesFragment.newInstance(favorites, categories, locations)
                switchFragment(R.id.fragment_wrapper, favoritesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.newInstance(events, categories)
                switchFragment(R.id.fragment_wrapper, homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                val mapFragment = MapFragment.newInstance()
                switchFragment(R.id.fragment_wrapper, mapFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_infos -> {
                val infosFragment = InfosFragment.newInstance()
                switchFragment(R.id.fragment_wrapper, infosFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadJsonData(data_file)
        loadFavorites(favs_file)

        if (findViewById<FrameLayout>(R.id.fragment_wrapper) != null) {
            if (savedInstanceState != null) {
                return
            }
            val homeFragment = HomeFragment.newInstance(events, categories)
            initFragment(R.id.fragment_wrapper, homeFragment)
        }
        navigation.getMenu().getItem(2).setChecked(true)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun initFragment(idContainer: Int, frag: Fragment){
        supportFragmentManager.beginTransaction()
                .add(idContainer, frag)
                .commit()
    }

    fun switchFragment(idContainer: Int, frag: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(idContainer, frag)
                .commit()
    }

    fun loadJsonData(data_file: String) {

        // Load & read json file from assets folder as a string
        val jsonString = application.assets.open(data_file).bufferedReader().use{
            it.readText()
        }

        // Access array of events as strings
        val jsonObject = JSONObject(jsonString)
        val items = jsonObject.getJSONArray("events")

        // Loop strings and access their properties
        for (i in 0..(items.length() - 1)) {
            val item = items.getJSONObject(i)
            val id = item.getInt("id")
            val title = item.getString("title")
            val catId = item.getInt("catId")
            val locationId = item.getInt("locationId")
            val image = item.getString("image")
            val bitmap = loadImage(image)
            val author = item.getString("author")
            val weekDay = item.getString("weekDay")
            val day = item.getString("day")
            val month = item.getString("month")
            val startTime = item.getString("startTime")
            val endTime = item.getString("endTime")
            val duration = item.getString("duration")
            val url = item.getString("url")
            val age = item.getString("age")
            events.add(LoadedData(id, title, catId, locationId, bitmap, author, weekDay, day, month, startTime, endTime, duration, url, age))
        }
    }

    fun loadFavorites(favs_file: String) {
        val jsonString = application.assets.open(favs_file).bufferedReader().use{
            it.readText()
        }
        val jsonArray = JSONArray(jsonString)

        for (i in 0..(jsonArray.length() - 1)) {
            val id = jsonArray.getString(i)

            for (j in 0 until events.size) {
                val event = events.get(j)
                val eventId = event.id.toString()
                if(eventId == id) {
                    favorites.add(event)
                }
            }
        }
    }

    // Get image from assets
    fun loadImage(image: String): Bitmap {
        val image_file = application.assets.open(image)
        val bitmap = BitmapFactory.decodeStream(image_file)
        return bitmap
    }
}