package com.example.mapsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsample.datasource.LoadDataCallback
import com.example.mapsample.datasource.SimpleCallback
import com.example.mapsample.datasource.repository.MainRepository
import com.example.mapsample.model.LocationData
import com.example.mapsample.model.LocationResponse
import com.example.mapsample.util.Util

class MainViewModel(
    val repository: MainRepository
) : ViewModel() {

    val isApiCalling = MutableLiveData<Boolean>()

    private val _locationData = MutableLiveData<ArrayList<LocationData>>()
    val locationData: LiveData<ArrayList<LocationData>>
        get() = _locationData


    fun getLocations() {
        isApiCalling.value = true
        repository.getLocations(object : LoadDataCallback<LocationResponse> {
            override fun onDataLoaded(response: LocationResponse) {
                isApiCalling.value = false
                Util.getFormattedAddress(response.poiList, object :
                    SimpleCallback<ArrayList<LocationData>> {
                    override fun invoke(obj: ArrayList<LocationData>) {
                        _locationData.value = obj
                    }
                })
            }

            override fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {
                isApiCalling.value = false
            }
        })
    }

}