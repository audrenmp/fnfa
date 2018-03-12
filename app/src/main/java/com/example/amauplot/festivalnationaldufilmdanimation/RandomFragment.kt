package com.example.amauplot.festivalnationaldufilmdanimation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RandomFragment : Fragment() {
    companion object {
        fun newInstance(events: ArrayList<LoadedData>, categories: Array<String>): HomeFragment {
            val args = Bundle()
            args.putParcelableArrayList("events", events)
            args.putStringArray("categories", categories)
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }
}