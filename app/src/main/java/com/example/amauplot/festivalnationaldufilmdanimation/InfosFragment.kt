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
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.widget.TextView


class InfosFragment : Fragment() {
    companion object {
        fun newInstance() = InfosFragment()
    }
    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_infos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewPrices: RecyclerView = this.activity.findViewById(R.id.recyclerview_tarifs)
        recyclerViewPrices.layoutManager = LinearLayoutManager(this.activity, LinearLayout.HORIZONTAL, false)

        val prices = ArrayList<ItemPrice>()

        prices.add(ItemPrice("6€", "Tarif unique"))
        prices.add(ItemPrice("8€", "Ciné concert"))
        prices.add(ItemPrice("3€", "Carte sortir"))
        prices.add(ItemPrice("25€", "Carnet fidélité"))
        prices.add(ItemPrice("3€", "Projection"))
        prices.add(ItemPrice("4€", "Atelier"))

        val adapterPrice = PriceViewAdapter(prices)
        recyclerViewPrices.adapter = adapterPrice

        val recyclerViewPartenaires: RecyclerView = this.activity.findViewById(R.id.recyclerview_partenaires)
        recyclerViewPartenaires.layoutManager = LinearLayoutManager(this.activity, LinearLayout.HORIZONTAL, false)

        val partenaires = ArrayList<ItemPartenaire>()

        partenaires.add(ItemPartenaire(BitmapFactory.decodeResource(resources, R.drawable.logo), "http://festival-film-animation.fr/"))
        partenaires.add(ItemPartenaire(BitmapFactory.decodeResource(resources, R.drawable.logo1), "http://festival-film-animation.fr/"))
        partenaires.add(ItemPartenaire(BitmapFactory.decodeResource(resources, R.drawable.logo2), "http://festival-film-animation.fr/"))
        partenaires.add(ItemPartenaire(BitmapFactory.decodeResource(resources, R.drawable.logo3), "http://festival-film-animation.fr/"))
        partenaires.add(ItemPartenaire(BitmapFactory.decodeResource(resources, R.drawable.logo4), "http://festival-film-animation.fr/"))
        partenaires.add(ItemPartenaire(BitmapFactory.decodeResource(resources, R.drawable.logo5), "http://festival-film-animation.fr/"))

        val adapterPartenaires = PartenaireViewAdapter(partenaires)
        recyclerViewPartenaires.adapter = adapterPartenaires

        val link: TextView = this.activity.findViewById(R.id.tv_link)
        link.setOnClickListener({
            val url = "http://www.festival-film-animation.fr/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        })
    }
}