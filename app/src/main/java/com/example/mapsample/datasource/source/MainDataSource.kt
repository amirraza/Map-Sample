package com.example.mapsample.datasource.source

import com.example.mapsample.datasource.DataSource
import com.example.mapsample.datasource.LoadDataCallback
import com.example.mapsample.model.LocationResponse

interface MainDataSource: DataSource {
    fun getLocations(loadDataCallback: LoadDataCallback<LocationResponse>)
}