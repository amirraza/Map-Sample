package com.example.mapsample.di

import com.example.mapsample.datasource.remote.MainRemoteDataSource
import com.example.mapsample.datasource.repository.MainRepository
import com.example.mapsample.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { MainRepository(get()) }
}

val dataSourceModule = module {
    single { MainRemoteDataSource() }
}