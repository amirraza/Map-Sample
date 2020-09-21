package com.example.mapsample.datasource

interface DataSource
interface Repository

abstract class AbstractFactory<T> {
    abstract fun create(which: Class<*>): T
}

/**
 * Generic type callback that is used for request or response from server or local
 * @param T generic type that will be converted into give data type at runtime
 */
@FunctionalInterface
interface SimpleCallback<T> {
    fun invoke(obj: T)
}

interface LoadDataCallback<T> {
    fun onDataLoaded(response: T)
    fun onDataNotAvailable(errorCode: Int, reasonMsg: String)
}
