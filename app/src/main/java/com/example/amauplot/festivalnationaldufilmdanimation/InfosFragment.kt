package com.example.amauplot.festivalnationaldufilmdanimation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


class InfosFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_infos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = this.activity.findViewById(R.id.recyclerview_tarifs)
        recyclerView.layoutManager = LinearLayoutManager(this.activity, LinearLayout.HORIZONTAL, false)

        val items = ArrayList<ItemPrice>()

        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))
        items.add(ItemPrice("5€", "Tarif unique"))

        val adapter = PriceViewAdapter(items)
        recyclerView.adapter = adapter
    }
}