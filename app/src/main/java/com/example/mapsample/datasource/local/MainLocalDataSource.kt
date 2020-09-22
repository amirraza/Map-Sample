package com.example.mapsample.datasource.local

import com.example.mapsample.datasource.LoadDataCallback
import com.example.mapsample.datasource.source.MainDataSource
import com.example.mapsample.model.LocationResponse

class MainLocalDataSource : MainDataSource {

    override fun getLocations(loadDataCallback: LoadDataCallback<LocationResponse>) {
        //Nothing to do here
    }
}