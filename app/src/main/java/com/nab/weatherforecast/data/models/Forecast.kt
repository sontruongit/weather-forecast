package com.nab.weatherforecast.data.models

class Forecast(
    val date: Long,
    val averageTemp: Float,
    val pressure: Int,
    val humidity: Int,
    val description: String
)