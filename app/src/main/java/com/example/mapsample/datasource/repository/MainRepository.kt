package com.example.mapsample.datasource.repository

import com.example.mapsample.datasource.LoadDataCallback
import com.example.mapsample.datasource.Repository
import com.example.mapsample.datasource.remote.MainRemoteDataSource
import com.example.mapsample.datasource.source.MainDataSource
import com.example.mapsample.model.LocationResponse

class MainRepository(
    private val remote: MainRemoteDataSource
) : MainDataSource, Repository {

    override fun getLocations(loadDataCallback: LoadDataCallback<LocationResponse>) {
        remote.getLocations(loadDataCallback)
    }
}