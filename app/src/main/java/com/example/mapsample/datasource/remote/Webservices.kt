package com.example.mapsample.datasource.remote

import com.example.mapsample.BuildConfig
import com.example.mapsample.config.Endpoints
import com.example.mapsample.model.LocationResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Webservices {

    @GET(Endpoints.LOCATIONS)
    fun getLocations(): Call<LocationResponse>


    companion object {

        val api by lazy { invoke(BuildConfig.BASE_URL) }

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
            interceptors().add(loggingInterceptor)
        }.build()

        private operator fun invoke(baseUrl: String): Webservices {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Webservices::class.java)
        }
    }
}