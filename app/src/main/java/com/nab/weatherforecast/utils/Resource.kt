package com.nab.weatherforecast.utils

class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> loading(message: String? = null): Resource<T> {
            return Resource(Status.LOADING, null, message)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }
    }
}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}
