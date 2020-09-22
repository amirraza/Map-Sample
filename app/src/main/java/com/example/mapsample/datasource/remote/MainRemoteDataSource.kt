package com.example.mapsample.datasource.remote

import com.example.mapsample.datasource.LoadDataCallback
import com.example.mapsample.datasource.source.MainDataSource
import com.example.mapsample.model.LocationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRemoteDataSource : MainDataSource {

    override fun getLocations(loadDataCallback: LoadDataCallback<LocationResponse>) {
        Webservices.api.getLocations().enqueue(object : Callback<LocationResponse> {
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if (response.isSuccessful)
                    loadDataCallback.onDataLoaded(response.body()!!)
                else
                    loadDataCallback.onDataNotAvailable(0, "")
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                loadDataCallback.onDataNotAvailable(0, t.localizedMessage)
            }
        })
    }
}