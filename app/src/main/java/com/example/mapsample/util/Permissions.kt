package com.example.mapsample.util

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.mapsample.AppClass

object Permissions {

    fun isLocationGranted() : Boolean {
        return ActivityCompat.checkSelfPermission(AppClass.ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}