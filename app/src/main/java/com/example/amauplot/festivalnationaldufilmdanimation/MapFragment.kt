package com.example.amauplot.festivalnationaldufilmdanimation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private var mMapView: MapView? = null
    private var mView: View? = null

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

    override fun onMapReady(p0: GoogleMap?) {
//        mGoogleMap = p0
//        mGoogleMap!!.mapType =  GoogleMap.MAP_TYPE_NORMAL
//        mGoogleMap!!.addMarker()

    }

}