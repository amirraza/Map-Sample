package com.example.mapsample.ui

import android.view.LayoutInflater
import android.view.View
import com.example.mapsample.AppClass
import com.example.mapsample.R
import com.example.mapsample.model.InfoWindowData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.item_info_window.view.*

class CustomInfoWindow(var data: InfoWindowData): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker?): View {
        val v = LayoutInflater.from(AppClass.ctx).inflate(R.layout.item_info_window, null)

        data = marker?.tag as InfoWindowData

        v.infoWindowTitleTV.text = data.title
        v.infoWindowLocationTV.text = data.locationName
        v.infoWindowCoordinatesTV.text = data.coordinate

        return v
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }

}