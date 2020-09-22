package com.example.mapsample.util

import com.example.mapsample.model.InfoWindowData
import com.example.mapsample.model.LocationData
import com.example.mapsample.ui.CustomInfoWindow
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

object MapUtils {

    fun showMarkers(googleMap: GoogleMap, list: ArrayList<LocationData>) {
        val latlngList = arrayListOf<LatLng>()
        list.forEach {
            val latlng = LatLng(
                it.coordinate?.latitude!!,
                it.coordinate.longitude
            )
            latlngList.add(latlng)
            val m = googleMap.addMarker(
                MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker())
            )
            val data = InfoWindowData(
                it.fleetType!!, it.locationName,
                "${it.coordinate.latitude},${it.coordinate.longitude}"
            )
            m.tag = data
            val window = CustomInfoWindow(data)
            googleMap.setInfoWindowAdapter(window)
        }
        setCameraBound(googleMap, latlngList)
    }

    fun setCameraBound(googleMap: GoogleMap, list: ArrayList<LatLng>) {
        val bound = LatLngBounds.Builder()
        list.forEach {
            bound.include(it)
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bound.build(), Util.dpToPx(30)))
    }

}