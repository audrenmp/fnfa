package com.example.amauplot.festivalnationaldufilmdanimation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.FrameLayout
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.ArrayList



class MainActivity : FragmentActivity() {

    val data_file = "afca.json"
    val favs_file = "afca_favorites"
    var events = ArrayList<LoadedData>()
    val favorites = ArrayList<LoadedData>()
    val favIds = ArrayList<Int>()
    val categories = arrayOf(
        "Compéts et panoramas",
        "Séances spéciales",
        "Longs métrages",
        "Professionnels",
        "Autour des films",
        "Salon des nouvelles écritures",
        "Cube animé",
        "Focus"
    )
    val locations = arrayOf(
        "TNB",
        "Arvor",
        "ESRA",
        "Grand Logis",
        "Esplanade Charles de Gaulles",
        "France 3 Bretagne"
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
        loadFavorites()

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
            val image = loadImage(id.toString() + ".jpg")
            val weekDay = item.getString("weekDay")
            val day = item.getInt("day")
            val month = item.getString("month")
            val startTime = item.getInt("startTime")
            val mins = item.getInt("mins")
            val url = item.getString("url")
            val age = item.getString("age")
            events.add(LoadedData(id, title, catId, locationId, image, weekDay, day, month, startTime, mins, url, age))
        }
    }

    // Get image from assets
    private fun loadImage(image: String): Bitmap {
        val image_file = application.assets.open(image)
        val bitmap = BitmapFactory.decodeStream(image_file)
        return bitmap
    }

    fun switchFavoriteBtn(id: Int, btn: ImageButton){
        if(favIds.contains(id)){
            btn.setImageResource(R.drawable.ic_coeur_on)
        } else {
            btn.setImageResource(R.drawable.ic_coeur_off)
        }
    }

    // Update favorite arrays and init new favorites fragment
    fun setFavorites(id: Int) {
        if(!favIds.contains(id)){
            favIds.add(id)
            favorites.add(findEventById(id))
        } else {
            favIds.remove(id)
            favorites.remove(findEventById(id))
        }

        updateStoredFavorites()

        val favoritesFragment = FavoritesFragment.newInstance(favorites, categories, locations)
        supportFragmentManager.beginTransaction().add(R.id.fragment_wrapper, favoritesFragment)
    }

    fun removeFavoritesFromFavoritesPage(id: Int) {
        if(favIds.contains(id)){
            favIds.remove(id)
            favorites.remove(findEventById(id))
        }
        updateStoredFavorites()
    }

    // Create/Open file and write new content
    private fun updateStoredFavorites(){
        val string: String = favIds.toString()
        FileHelper.writeFile(favs_file, string, this)
    }

    // Open file and get the content into array
    private fun loadFavorites(){
        val favsString = FileHelper.readFile(favs_file, this)
        if(favsString != "") {
            val favsArray = favsString.removeSurrounding("[", "]").split(", ").map { it.toInt() }

            for (i in 0 until favsArray.size) {
                val id = favsArray[i]
                favIds.add(id)
                favorites.add(findEventById(id))
            }
        }
    }

    private fun findEventById(id: Int): LoadedData{
        val event = events.first{
            it.id == id
        }
        return event
    }
}