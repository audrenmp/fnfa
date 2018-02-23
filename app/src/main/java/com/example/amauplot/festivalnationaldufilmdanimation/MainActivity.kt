package com.example.amauplot.festivalnationaldufilmdanimation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : FragmentActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_calendar -> {
                 val calendarFragment = CalendarFragment.newInstance()
                 switchFragment(R.id.fragment_wrapper, calendarFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favoris -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.newInstance()
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

        if (findViewById<FrameLayout>(R.id.fragment_wrapper) != null) {
            if (savedInstanceState != null) {
                return
            }
            val homeFragment = HomeFragment()
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

}