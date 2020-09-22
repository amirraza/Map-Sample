package com.example.mapsample.util

import android.location.Geocoder
import android.util.DisplayMetrics
import com.example.mapsample.AppClass
import com.example.mapsample.datasource.SimpleCallback
import com.example.mapsample.model.LocationData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

object Util {

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics =
            AppClass.ctx.resources.displayMetrics
        val px =
            dp * (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return Math.round(px)
    }

    fun getFormattedAddress(list: ArrayList<LocationData>, simpleCallback: SimpleCallback<ArrayList<LocationData>>) {
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach {
                val fetchedAddress = Geocoder(AppClass.ctx,
                    Locale.getDefault()).getFromLocation(it.coordinate?.latitude!!, it.coordinate.longitude, 1)

                var strAddress = ""
                fetchedAddress[0].thoroughfare?.let {
                    strAddress = "$strAddress $it"
                }

                fetchedAddress[0].featureName?.let {
                    if (!strAddress.contains(fetchedAddress[0].featureName))
                        strAddress = "$strAddress $it"
                }

                fetchedAddress[0].locality?.let {
                    if (!strAddress.contains(fetchedAddress[0].locality))
                        strAddress = "$strAddress $it"
                }

                it.locationName = strAddress
            }

            CoroutineScope(Dispatchers.Main).launch {
                simpleCallback.invoke(list)
            }
        }
    }
}