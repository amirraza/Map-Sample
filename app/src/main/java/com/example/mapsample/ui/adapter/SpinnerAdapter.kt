package com.example.mapsample.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.mapsample.R
import com.example.mapsample.model.LocationData
import kotlinx.android.synthetic.main.item_location_spinner.view.*

class SpinnerAdapter(
    val context: Context,
    val list: ArrayList<LocationData>
) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_location_spinner, null)

        view.locationTitle.text = "${list[position].fleetType}"
        view.locationCoordinates.text = "${list[position].locationName}"

        return view
    }
}