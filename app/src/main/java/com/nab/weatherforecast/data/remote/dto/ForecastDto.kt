package com.nab.weatherforecast.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName(value = "dt")
    val date: Long,
    val temp: TemperatureDto,
    val pressure: Int,
    val humidity: Int,
    val weather: List<WeatherDto>
)