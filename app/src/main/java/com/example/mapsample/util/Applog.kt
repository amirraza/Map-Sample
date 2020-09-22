package com.example.mapsample.util

import android.widget.Toast
import org.koin.core.context.KoinContextHandler

object Applog {
    fun toast(msg: String) {
        Toast.makeText(KoinContextHandler.get().get(), msg, Toast.LENGTH_SHORT).show()
    }
}