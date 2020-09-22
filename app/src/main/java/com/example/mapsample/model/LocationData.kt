package com.example.mapsample.model

data class LocationResponse(val poiList: ArrayList<LocationData>)
data class LocationData(
    val id: Long = 0,
    val coordinate: Coordinate? = null,
    val fleetType: String? = null,
    val heading: Double = 0.0,
    var locationName: String = ""
)

data class Coordinate(val latitude: Double = 0.0, val longitude: Double = 0.0)