package com.example.amauplot.festivalnationaldufilmdanimation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CalendarFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_calendar,
                container, false)
    }
}