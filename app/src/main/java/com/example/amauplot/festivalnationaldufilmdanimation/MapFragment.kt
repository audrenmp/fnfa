package com.example.amauplot.festivalnationaldufilmdanimation

import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.ActivityCompat
import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.location.Location
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.GoogleMap



class MapFragment : Fragment(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private var mMapView: MapView? = null
    private var mView: View? = null
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var lastLocation: Location
    private var mMap: GoogleMap? = null

    private var currentMarker: Marker? = null

    companion object {
        fun newInstance() = MapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater?.inflate(R.layout.fragment_map, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mMapView = mView!!.findViewById(R.id.mapView)

        mMapView?.apply {
            this.onCreate(null)
            this.onResume()
            this.getMapAsync(this@MapFragment)
        }
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }

    private fun placeMarkerOnMap(location: LatLng, title: String) {
        val markerOptions = MarkerOptions().position(location).title(title)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.drawable.pin)))
        mGoogleMap!!.addMarker(markerOptions)
    }

    override fun onMapReady(p0: GoogleMap?) {
        mGoogleMap = p0
        mGoogleMap!!.mapType =  GoogleMap.MAP_TYPE_NORMAL

        val cinema_arvor = LatLng(48.088322, -1.678414)
        val title_arvor = "Arvor"
        placeMarkerOnMap(cinema_arvor, title_arvor)

        val cinema_tnb = LatLng(48.108050, -1.672144)
        val title_tnb = "TNB"
        placeMarkerOnMap(cinema_tnb, title_tnb)

        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(cinema_tnb, 13F))

        mGoogleMap!!.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                marker.hideInfoWindow()
                val displayInfos: ConstraintLayout = mView!!.findViewById(R.id.mapboxinfos)
                displayInfos.setVisibility(ConstraintLayout.VISIBLE)
                if (marker.getTitle() == "Arvor") {
                    val cineTitle: TextView = mView!!.findViewById(R.id.cinematv)
                    cineTitle.text = "Cinema Arvor"
                    val cineDesc: TextView = mView!!.findViewById(R.id.descriptioncinematv)
                    cineDesc.text = "29 rue d’Antrain\n" +
                            "02 99 38 78 04\n" +
                            "\n" +
                            "Bus C1 - C5 - 9 - 12\n" + "Arrêt : Sainte-Anne ou Hôtel Dieu / Métro Sainte-Anne"
                }



                if (marker.getTitle() == "TNB") {
                    val cineTitle: TextView = mView!!.findViewById(R.id.cinematv)
                    cineTitle.text = "TNB"
                    val cineDesc: TextView = mView!!.findViewById(R.id.descriptioncinematv)
                    cineDesc.text = "1 rue Saint-Hélier\n" +
                            "02 99 31 55 33\n" +
                            "\n" +
                            "Ouverture au grand public à partir de 13h\n" +
                            "\n" +
                            "Bus C1 - C2 - 11\n" + "Arrêt : Liberté TNB / Métro Charles de Gaulle"
                }
                return false
            }
        })

        setUpMap()
    }

}