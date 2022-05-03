package com.nab.weatherforecast.data.remote

import com.nab.weatherforecast.data.remote.dto.DailyForecastDto
import com.nab.weatherforecast.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("daily")
    suspend fun getDailyForecast(
        @Query("appId") appId: String = Constants.APP_ID,
        @Query("q") city: String,
        @Query("ctn") daysCount: Int = Constants.DEFAULT_DAYS_COUNT,
        @Query("units") units: String = Constants.DEFAULT_UNITS
    ): Response<DailyForecastDto>
}