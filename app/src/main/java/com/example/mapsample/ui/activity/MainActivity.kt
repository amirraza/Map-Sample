package com.example.mapsample.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.mapsample.R
import com.example.mapsample.model.LocationData
import com.example.mapsample.ui.adapter.SpinnerAdapter
import com.example.mapsample.util.MapUtils
import com.example.mapsample.util.Permissions
import com.example.mapsample.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewmodel: MainViewModel by viewModel()
    private lateinit var googleMap: GoogleMap
    private val markersList = arrayListOf<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Permissions.isLocationGranted()) {
            initViews()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                999
            )
        }
    }

    private fun initViews() {
        (mapView as SupportMapFragment).getMapAsync(this)

        viewmodel.isApiCalling.observe(this, Observer {
            spinnerLoader.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewmodel.locationData.observe(this, Observer {
            locationSpinner.adapter = SpinnerAdapter(this, it)
            markersList.clear()
            it.forEach { data ->
                markersList.add(LatLng(data.coordinate?.latitude!!, data.coordinate.longitude))
            }
            if (::googleMap.isInitialized) {
                googleMap.setOnMapLoadedCallback {
                    MapUtils.showMarkers(googleMap, it)
                }
            }
        })

        locationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val data = parent?.getItemAtPosition(position) as LocationData
                googleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(data.coordinate?.latitude!!, data.coordinate.longitude), 17f
                    )
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        viewmodel.getLocations()
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            googleMap = it

            googleMap.setOnMapClickListener {
                MapUtils.setCameraBound(googleMap, markersList)
            }

            googleMap.setOnMarkerClickListener {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position!!, 17f))
                it?.showInfoWindow()
                false
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 999 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initViews()
        } else {
            finish()
        }
    }
}