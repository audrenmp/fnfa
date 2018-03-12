package com.example.amauplot.festivalnationaldufilmdanimation

import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import android.support.v4.app.ActivityCompat
import android.Manifest
import android.widget.Toast

class MapFragment : Fragment(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private var mMapView: MapView? = null
    private var mView: View? = null
    private val LOCATION_REQUEST_CODE = 101
    private var mMap: GoogleMap? = null

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
//        if (mGoogleMap != null) {
//            val permission = ContextCompat.checkSelfPermission(this.context,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//
//            if (permission == PackageManager.PERMISSION_GRANTED) {
//                mGoogleMap?.isMyLocationEnabled = true
//            } else {
//                requestPermission(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        LOCATION_REQUEST_CODE)
//            }
//        }

    }

}